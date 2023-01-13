package cn.cpoet.yunzhi.note.auth.configuration;

import cn.cpoet.yunzhi.note.auth.resolver.SubjectArgResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author CPoet
 */
@RequiredArgsConstructor
@Import(AuthenticateWebMvcConfig.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AuthenticateServletConfig {
    @Bean
    public SubjectArgResolver subjectArgResolver() {
        return new SubjectArgResolver();
    }
}
