package cn.cpoet.yunzhi.note.web.comm.configuration;

import cn.cpoet.yunzhi.note.core.support.SnowFlakeIdGenerator;
import cn.cpoet.yunzhi.note.web.comm.configuration.auto.SnowFlakeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CPoet
 */
@Configuration
public class IdGeneratorConfig {
    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "note.snow-flake")
    public SnowFlakeProperties snowFlakeProperties() {
        return new SnowFlakeProperties();
    }

    @Bean
    @RefreshScope
    public SnowFlakeIdGenerator snowFlakeIdGenerator(SnowFlakeProperties snowFlakeProperties) {
        return new SnowFlakeIdGenerator(snowFlakeProperties.getWorkerId(), snowFlakeProperties.getSequence());
    }
}
