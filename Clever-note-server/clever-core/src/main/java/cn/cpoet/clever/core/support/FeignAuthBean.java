package cn.cpoet.clever.core.support;

import lombok.Data;

/**
 * Feign调用验证Bean
 *
 * @author CPoet
 */
@Data
public class FeignAuthBean {
    /**
     * 客户端名称
     */
    private String client;

    /**
     * 发起的时间
     */
    private Long timeMillis;
}
