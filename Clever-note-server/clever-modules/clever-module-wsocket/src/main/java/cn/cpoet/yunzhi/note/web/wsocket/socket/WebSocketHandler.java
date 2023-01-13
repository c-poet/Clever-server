package cn.cpoet.yunzhi.note.web.wsocket.socket;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.core.exception.ReqsException;
import cn.cpoet.yunzhi.note.core.constant.CommReqsStatus;
import cn.cpoet.yunzhi.note.web.wsocket.component.SocketSessionHolder;
import cn.cpoet.yunzhi.note.web.wsocket.constant.WSocketConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * socket 处理
 *
 * @author CPoet
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final SocketSessionHolder socketSessionHolder;

    /**
     * 建立socket连接
     *
     * @param session WebSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 获取请求上下文信息
        Object obj = session.getAttributes().get(WSocketConst.SOCKET_SUBJECT_SESSION_CACHE);
        if (!(obj instanceof Subject)) {
            throw new ReqsException(CommReqsStatus.ACCESS_DENIED, "无效的用户信息");
        }
        Subject subject = (Subject) obj;
        log.info("新的客户端连接[uid={}, sessionId={}]", subject.getUid(), session.getId());
        socketSessionHolder.addSession(subject, session);
    }

    /**
     * 处理文本消息
     *
     * @param session WebSocketSession
     * @param message TextMessage
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message);
    }

    /**
     * 处理异常
     *
     * @param session   WebSocketSession
     * @param exception Throwable
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.info("socket通信出现错误.");
    }

    /**
     * 关闭连接后处理
     *
     * @param session WebSocketSession
     * @param status  CloseStatus
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Subject subject = (Subject) session.getAttributes().get(WSocketConst.SOCKET_SUBJECT_SESSION_CACHE);
        log.info("客户端[uid={},sessionId={}]关闭", subject.getUid(), session.getId());
        socketSessionHolder.removeSession(subject, session);
    }
}
