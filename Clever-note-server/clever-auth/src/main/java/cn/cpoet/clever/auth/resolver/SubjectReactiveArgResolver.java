package cn.cpoet.clever.auth.resolver;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.clever.core.support.ReactiveRequestWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author CPoet
 */
public class SubjectReactiveArgResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Subject.class == parameter.getParameterType();
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter,
                                        BindingContext bindingContext,
                                        ServerWebExchange exchange) {
        return Mono.just(AppContextUtil.authContext().getSubject(ReactiveRequestWrapper.wrapper(exchange)));
    }
}