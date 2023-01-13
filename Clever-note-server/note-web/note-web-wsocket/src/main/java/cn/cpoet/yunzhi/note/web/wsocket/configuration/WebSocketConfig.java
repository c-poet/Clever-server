package cn.cpoet.yunzhi.note.web.wsocket.configuration;

import cn.cpoet.yunzhi.note.web.wsocket.socket.WebSocketHandler;
import cn.cpoet.yunzhi.note.web.wsocket.socket.WebSocketHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author CPoet
 */
@Configuration
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final WebSocketHandlerInterceptor webSocketHandlerInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/websocket")
            .addInterceptors(webSocketHandlerInterceptor)
            // 允许跨域
            .setAllowedOrigins("*");

        registry.addHandler(webSocketHandler, "/sockjs")
            .addInterceptors(webSocketHandlerInterceptor)
            .setAllowedOrigins("*")
            .withSockJS();
    }
}
