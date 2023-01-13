package cn.cpoet.yunzhi.note.auth.configuration.auto;

import lombok.Data;

import java.time.Duration;

/**
 * 认证Token配置
 *
 * @author CPoet
 */
@Data
public class AuthTokenProperties {
    /**
     * 携带Token的参数
     */
    private String tokenParam = "token";

    /**
     * 携带Token的请求头信息
     */
    private String tokenHeader = "Authentication";

    /**
     * Token有效时长，默认30分钟
     */
    private Duration tokenDuration = Duration.ofMinutes(30);
}
