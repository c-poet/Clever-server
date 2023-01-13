package cn.cpoet.clever.core.configuration;

import cn.cpoet.yunzhi.note.annotation.feign.FeignTarget;
import cn.cpoet.clever.core.configuration.auto.FeignProperties;
import cn.cpoet.clever.core.support.FeignRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * feign全局配置
 *
 * <p>
 * 注意：应用启动时，如果存在Feign调用的地方，得确保是在Feign全局配置加载完成之后（例如：{@link FeignRequestInterceptor}拦截器之后），
 * 否则调用{@link FeignTarget}接口会出现验证失败的情况。
 * </p>
 *
 * @author CPoet
 */
@Slf4j
public class CoreFeignConfig {

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.feign")
    public FeignProperties feignProperties() {
        return new FeignProperties();
    }

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
