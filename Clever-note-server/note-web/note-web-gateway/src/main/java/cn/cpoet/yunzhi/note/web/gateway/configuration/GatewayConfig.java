package cn.cpoet.yunzhi.note.web.gateway.configuration;

import cn.cpoet.yunzhi.note.web.gateway.configuration.auto.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author CPoet
 */
@Slf4j
@Configuration
@EnableScheduling
@EnableFeignClients("cn.cpoet.yunzhi.note.web.gateway.feign")
public class GatewayConfig {
    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.security")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
