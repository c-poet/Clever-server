package cn.cpoet.yunzhi.note.web.gateway.filter;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.api.core.AppContext;
import cn.cpoet.yunzhi.note.api.core.TraceInfo;
import cn.cpoet.yunzhi.note.core.support.ReactiveRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 网关拦截器
 *
 * @author CPoet
 */
@Component
@RequiredArgsConstructor
public class CustomGatewayFilter implements OrderedWebFilter {

    private final AppContext appContext;

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        TraceInfo traceInfo = appContext.getTraceInfo(ReactiveRequestWrapper.wrapper(exchange));
        // 跨度为0时将链路信息填充至请求中
        if (traceInfo.getSpanId() == SystemConst.DEFAULT_SPAN_ID) {
            ServerHttpRequest.Builder reqsBuilder = exchange.getRequest().mutate();
            reqsBuilder.header(SystemConst.SPAN_ID, String.valueOf(traceInfo.getSpanId()));
            reqsBuilder.header(SystemConst.TRACE_ID, traceInfo.getTraceId());
            chain.filter(exchange.mutate().request(reqsBuilder.build()).build());
        }
        return chain.filter(exchange);
    }
}
