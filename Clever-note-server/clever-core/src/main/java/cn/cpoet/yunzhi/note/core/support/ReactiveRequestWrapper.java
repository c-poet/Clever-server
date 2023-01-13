package cn.cpoet.yunzhi.note.core.support;

import cn.cpoet.yunzhi.note.annotation.core.RequestWrapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * Reactive适配
 *
 * @author CPoet
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactiveRequestWrapper implements RequestWrapper {

    private final static RequestWrapper NONE = new ReactiveRequestWrapper(null);

    private final ServerWebExchange webExchange;

    @Override
    public boolean requesting() {
        return webExchange != null;
    }

    @Override
    public String getRemoteAddr() {
        return Optional
            .ofNullable(webExchange)
            .map(ServerWebExchange::getRequest)
            .map(ServerHttpRequest::getRemoteAddress)
            .map(InetSocketAddress::getHostString)
            .orElse(null);
    }

    @Override
    public String getHeader(String name) {
        return Optional
            .ofNullable(webExchange)
            .map(ServerWebExchange::getRequest)
            .map(ServerHttpRequest::getHeaders)
            .map(httpHeaders -> httpHeaders.get(name))
            .map(headers -> CollectionUtils.isEmpty(headers) ? null : headers.get(0))
            .orElse(null);
    }

    @Override
    public String getParameter(String name) {
        return Optional
            .ofNullable(webExchange)
            .map(ServerWebExchange::getRequest)
            .map(ServerHttpRequest::getQueryParams)
            .map(params -> params.getFirst(name))
            .orElse(null);
    }

    @Override
    public Object getAttribute(String name) {
        return Optional
            .ofNullable(webExchange)
            .map(exchange -> exchange.getAttribute(name))
            .orElse(null);
    }

    @Override
    public void setAttribute(String name, Object o) {
        if (webExchange != null) {
            webExchange.getAttributes().put(name, o);
        }
    }

    public static RequestWrapper wrapper(ServerWebExchange exchange) {
        if (exchange == null) {
            return NONE;
        }
        return new ReactiveRequestWrapper(exchange);
    }
}
