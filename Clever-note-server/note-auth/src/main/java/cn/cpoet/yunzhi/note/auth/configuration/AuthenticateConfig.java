package cn.cpoet.yunzhi.note.auth.configuration;

import cn.cpoet.yunzhi.note.api.auth.AuthContext;
import cn.cpoet.yunzhi.note.auth.configuration.auto.AuthTokenProperties;
import cn.cpoet.yunzhi.note.auth.configuration.auto.AuthenticateProperties;
import cn.cpoet.yunzhi.note.auth.core.DefaultAuthContext;
import cn.cpoet.yunzhi.note.auth.interceptor.AuthenticateFeignInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 认证配置
 *
 * @author CPoet
 */
@Slf4j
@RequiredArgsConstructor
@ComponentScan({"cn.cpoet.yunzhi.note.auth.component", "cn.cpoet.yunzhi.note.auth.aspect"})
@Import({AuthenticateReactiveConfig.class, AuthenticateServletConfig.class})
public class AuthenticateConfig {
    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.auth")
    public AuthenticateProperties authenticateProperties() {
        return new AuthenticateProperties();
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.auth.token")
    public AuthTokenProperties authTokenProperties() {
        return new AuthTokenProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthContext authContext() {
        return new DefaultAuthContext();
    }

    @Bean
    @RefreshScope
    public AuthenticateFeignInterceptor authenticateFeignInterceptor() {
        return new AuthenticateFeignInterceptor();
    }
}
