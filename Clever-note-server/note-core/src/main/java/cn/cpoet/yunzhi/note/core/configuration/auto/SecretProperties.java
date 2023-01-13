package cn.cpoet.yunzhi.note.core.configuration.auto;

import cn.cpoet.yunzhi.note.api.core.SystemKeyHolder;
import lombok.Data;

/**
 * 密钥配置
 *
 * @author CPoet
 */
@Data
public class SecretProperties {
    /**
     * 全局加密开关
     */
    private Boolean enableSysSecret = Boolean.TRUE;

    /**
     * 系统级加密-密钥
     */
    private String sysKey = SystemKeyHolder.DEFAULT_SECRET_KEY;

    /**
     * 常规-密钥
     */
    private String secretKey = SystemKeyHolder.DEFAULT_SECRET_KEY;

    /**
     * 常规-私钥
     */
    private String privateKey = SystemKeyHolder.DEFAULT_PRIVATE_KEY;

    /**
     * 常规-公钥
     */
    private String publicKey = SystemKeyHolder.DEFAULT_PUBLIC_KEY;
}
