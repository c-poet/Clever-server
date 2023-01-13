package cn.cpoet.yunzhi.note.web.wsocket.component;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户Session
 *
 * @author CPoet
 */
@Component
@RequiredArgsConstructor
public class SocketSessionHolder {

    private final RedisTemplate redisTemplate;

    private final Map<Long, List<WebSocketSession>> memberWebSocketSession = new ConcurrentHashMap<>();

    public void addSession(Subject subject, WebSocketSession webSocketSession) {
        List<WebSocketSession> webSocketSessions = memberWebSocketSession.computeIfAbsent(subject.getUid(), k -> new LinkedList<>());
        synchronized (this) {
            webSocketSessions.add(webSocketSession);
        }
    }

    public void removeSession(Subject subject, WebSocketSession webSocketSession) {
        List<WebSocketSession> webSocketSessions = memberWebSocketSession.get(subject.getUid());
        if (!CollectionUtils.isEmpty(webSocketSessions)) {
            synchronized (this) {
                webSocketSessions.removeIf(session -> Objects.equals(session.getId(), webSocketSession.getId()));
            }
        }
    }
}
