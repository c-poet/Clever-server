package cn.cpoet.clever.core.configuration;

import cn.cpoet.clever.annotation.core.RequestWrapper;
import cn.cpoet.clever.core.support.ReactiveRequestWrapper;
import cn.cpoet.clever.core.filter.reactive.TraceWebFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class CoreReactiveConfig {

    @Bean
    public TraceWebFilter reactiveTraceWebFilter() {
        return new TraceWebFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestWrapper requestWrapper() {
        return ReactiveRequestWrapper.wrapper(null);
    }

    /**
     * Reactive模式下缺失问题（Feign不可用问题）
     *
     * @param converters {@link HttpMessageConverter}
     * @return {@link HttpMessageConverters}
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
