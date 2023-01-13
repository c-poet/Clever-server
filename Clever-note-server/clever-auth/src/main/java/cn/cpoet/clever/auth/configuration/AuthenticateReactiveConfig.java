package cn.cpoet.clever.auth.configuration;

import cn.cpoet.clever.auth.resolver.SubjectReactiveArgResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author CPoet
 */
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class AuthenticateReactiveConfig {
    @Bean
    public SubjectReactiveArgResolver subjectReactiveArgResolver() {
        return new SubjectReactiveArgResolver();
    }
}
