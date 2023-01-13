package cn.cpoet.clever.core.configuration.auto;

import lombok.Data;

import java.time.Duration;

/**
 * @author CPoet
 */
@Data
public class FeignProperties {
    /**
     * 间隔时间，默认3分钟
     */
    private Duration intervalTime = Duration.ofMinutes(3);
}
