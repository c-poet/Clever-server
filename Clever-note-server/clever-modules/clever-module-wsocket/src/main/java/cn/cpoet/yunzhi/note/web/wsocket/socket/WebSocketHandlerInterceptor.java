package cn.cpoet.yunzhi.note.web.wsocket.socket;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.annotation.constant.SubjectType;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.core.support.ServletRequestWrapper;
import cn.cpoet.yunzhi.note.web.wsocket.constant.WSocketConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * socket拦截器
 *
 * @author CPoet
 */
@Slf4j
@Component
public class WebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        Subject subject = AppContextUtil.authContext().getSubject(ServletRequestWrapper.wrapper(servletRequest));
        log.debug("用户尝试连接[uid={}]", subject.getUid());
        // 缓存当前登录主体的信息
        attributes.put(WSocketConst.SOCKET_SUBJECT_SESSION_CACHE, subject);
        return SubjectType.STAFF.equals(subject.getType());
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception ex) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        Subject subject = AppContextUtil.authContext().getSubject(ServletRequestWrapper.wrapper(servletRequest));
        log.debug("握手成功[uid={}]", subject.getUid());
    }
}
