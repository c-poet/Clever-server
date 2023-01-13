package cn.cpoet.yunzhi.note.auth.configuration.auto;

import lombok.Data;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * 认证相关配置
 *
 * @author CPoet
 */
@Data
public class AuthenticateProperties {
    /**
     * 需要认证的url
     */
    private List<String> authUrl;

    /**
     * 跳过认证的url
     */
    private List<String> ignored;

    /**
     * 访问权限匹配，当且仅当{@link #authUrl}有效时
     */
    private Map<String, AuthPermissionMatchProperties> match;
}
