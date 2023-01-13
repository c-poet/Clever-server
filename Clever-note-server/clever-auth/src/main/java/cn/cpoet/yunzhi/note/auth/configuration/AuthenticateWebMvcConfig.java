package cn.cpoet.yunzhi.note.auth.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author CPoet
 */
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AuthenticateWebMvcConfig implements WebMvcConfigurer {
    private final List<HandlerMethodArgumentResolver> handlerMethodArgumentResolvers;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        if (!CollectionUtils.isEmpty(handlerMethodArgumentResolvers)) {
            resolvers.addAll(handlerMethodArgumentResolvers);
        }
    }
}
