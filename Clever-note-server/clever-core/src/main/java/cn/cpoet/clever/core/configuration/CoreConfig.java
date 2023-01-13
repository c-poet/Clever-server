package cn.cpoet.clever.core.configuration;

import cn.cpoet.clever.annotation.core.AppContext;
import cn.cpoet.clever.annotation.core.SystemKeyHolder;
import cn.cpoet.clever.core.aspect.FeignTargetAspect;
import cn.cpoet.clever.core.configuration.auto.SecretProperties;
import cn.cpoet.clever.core.support.DefaultAppContext;
import cn.cpoet.clever.core.support.SimpleUUIDGenerator;
import cn.cpoet.clever.core.support.SystemKeyHolderImpl;
import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

/**
 * @author CPoet
 */
@ComponentScan("cn.cpoet.clever.core.component")
@EnableFeignClients("cn.cpoet.clever.core.feign")
@Import({
    ConvertersConfig.class,
    CoreFeignConfig.class,
    OpenApiConfig.class,
    CoreReactiveConfig.class,
    CoreServletConfig.class,
    CacheConfig.class
})
public class CoreConfig {
    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.secret")
    public SecretProperties secretProperties() {
        return new SecretProperties();
    }

    @Bean
    @RefreshScope
    @ConditionalOnMissingBean
    public SystemKeyHolder systemKeyHolder() {
        return new SystemKeyHolderImpl();
    }

    @Bean
    @Primary
    @RefreshScope
    @ConfigurationProperties(prefix = "spring.task.execution")
    public TaskExecutionProperties taskExecutionProperties() {
        TaskExecutionProperties properties = new TaskExecutionProperties();
        properties.setThreadNamePrefix(SystemConst.SYSTEM_PREFIX_);
        TaskExecutionProperties.Pool pool = properties.getPool();
        pool.setCoreSize(5);
        pool.setQueueCapacity(1 << 8);
        pool.setMaxSize(1 << 8);
        pool.setKeepAlive(Duration.ofSeconds(30));
        return properties;
    }

    @Bean
    @Primary
    @RefreshScope
    @ConfigurationProperties(prefix = "spring.task.scheduling")
    public TaskSchedulingProperties taskSchedulingProperties() {
        TaskSchedulingProperties taskSchedulingProperties = new TaskSchedulingProperties();
        taskSchedulingProperties.setThreadNamePrefix(SystemConst.SYSTEM_PREFIX_ + "sd-");
        return taskSchedulingProperties;
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnMissingBean
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public AppContext appContext() {
        return AppContextUtil.initialize(DefaultAppContext::new);
    }

    @Bean
    public FeignTargetAspect feignTargetAspect() {
        return new FeignTargetAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public SimpleUUIDGenerator simpleUuidGenerator() {
        return SimpleUUIDGenerator.INSTANCE;
    }
}
