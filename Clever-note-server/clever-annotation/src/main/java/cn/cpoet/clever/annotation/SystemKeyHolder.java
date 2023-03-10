package cn.cpoet.clever.annotation;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 系统安全类密钥
 * <p>非对称算法使用RSA，对称算法使用DES</p>
 *
 * @author CPoet
 */
public interface SystemKeyHolder {
    /**
     * 默认私钥
     */
    String DEFAULT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOhWjyM/JWcl1+Q7JZESUdV9H7VO6kYOvigDQRJf7Nw6HNwLzW9bCeAIsRKhI+dFMG1wO/F7sL2l9WfvXKTqC/lyOxT3JGU5Za0krjyIQPrSPQbvKR8wRFsqaQoPCN4qX6lVUtByh3fffGj9mgWPWK0tsLOJVNDQbyM19OKN8biQyOunu5YY59tF16RZ6M69125hYoZPVeaK9hSbYGjB7Ox9AjUZk2CN/m1MM4wzaG4ullpUikUD7Clko020MKABv2WIWPWb/hRUblLtyF7ipXc/dFGbUoq4DGS0to2fI4IP6maufm/c3I5ihUWj82M2Qiw+1rSxHF67vk14dYCvClAgMBAAECggEAP9FZVusP3snDN6DUobFdry/95M75NT35IilKJ0ueN0EcmOdvJ8pIVdfkbcEq/jBUZUGueckEhqA4BKFBKl/e9yuUXfc3MmqHbw+vXSQSjnp3CQRFNjlddmAy2ZxXWxFcRULNKyaDZk5Pvuf3CSgMeydD0lh58lCdBaMfT/5ZORiQHMMg5yB/pHvcZ1IS0i0J9X8f8o6Ea2HRxHnzB6trs9G5fipZ+lxfvDFLZpYns7nCSdTYNQrrgrDZXwyy8yESW+375gLJSAsQ4WGBzlHpEk/Aph2dR+9zUWwVHdZkaXyvM6lrnnagOtgo4x9vJfHtHuUXcAQAl2FlyCRVqsjqWQKBgQDDf1LeO1p2K+x8+e/2deDj0F/NhBXG0fZjhHlfH4XfnMfoPo+8pLmikR23j2AL9MHsjbAL0suwGcenUm2vp/ksrucuw7ASFaSGNnggtelLwSPOjOkQyiwLEvNGnUyUX+6qbq3bkAzGz+R7akK29Z3kZ1FAcWCdhgOi5aVsIu6G9wKBgQC6oO/YKhPyXRc8VQJu4QusAbFz1+nbx+dfikq8C8x8O0lUMCokIQjx2qb6+727MKyvjpDhzGDof1mVbwhq/p0j9dcOfsSSJvFMJmWctMB3Df4P/4vF5/3M0sVoI5yPjVbf3E+KIjJyjjo5OsOcGE6RS1BsnzstFrJnLlGv4ZHSQwKBgBYtvK3/l8yuQP8Qr9uLFf9iZWwAXyQWL9isb8SN4/cxZzB+4HmrSnAZ/IAVoPTaSKHYlS9SM89/rTnbTRxPdyJNmWahxrjWgbrENS6o1JXGjJRjQhGojgSXmsfZEpYGqCHnuoudNQXdEfO6Y2U0SjxeSEaCVk6opIL3gKPa9qqTAoGADp9zi31oiLHwkyKaCTQK5RRQ4iHFyhm0sFC2oFtQT1+Z1EXd12zbNZALS1kUOamLrkm+YmLQKQdpboL/ptqCV4H4ssMOBeGVQIE1rvyeo/VttbUxL3WI9laBbzJzHvBVIT8y4EDjsQQh3cAT+ZQR++Jpz8j+Kv8/RURk1CIZed8CgYEAsNsSF9Y3Ao6IxJVzMgRYTNmUja9wkDCbjjP/2qETRV0xUR6/miekdh8YrgNO3LvKBJHUowZSU8J3FLcmYvjB2bYz9gHWOTk/vcAOxWC+EyS5+Q+ub0yyX0fq2oCyUVsQbIDFS294TXG4xxwhswPGAYnTAoDQ6WV7jtomcuqiHgg=";

    /**
     * 默认公钥
     */
    String DEFAULT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjoVo8jPyVnJdfkOyWRElHVfR+1TupGDr4oA0ESX+zcOhzcC81vWwngCLESoSPnRTBtcDvxe7C9pfVn71yk6gv5cjsU9yRlOWWtJK48iED60j0G7ykfMERbKmkKDwjeKl+pVVLQcod333xo/ZoFj1itLbCziVTQ0G8jNfTijfG4kMjrp7uWGOfbRdekWejOvdduYWKGT1XmivYUm2BowezsfQI1GZNgjf5tTDOMM2huLpZaVIpFA+wpZKNNtDCgAb9liFj1m/4UVG5S7che4qV3P3RRm1KKuAxktLaNnyOCD+pmrn5v3NyOYoVFo/NjNkIsPta0sRxeu75NeHWArwpQIDAQAB";

    /**
     * 默认密钥
     */
    String DEFAULT_SECRET_KEY = "FnYjs5g0EPs=";

    /**
     * 是否开启系统级加密
     * <p>针对配置文件的敏感数据进行加密和解密操作</p>
     *
     * @return bool
     */
    boolean isSysSecret();

    /**
     * 获取系统级密钥
     *
     * @return 系统级密钥
     */
    SecretKey getSysKey();

    /**
     * 获取常规密钥
     *
     * @return 常规密钥
     */
    SecretKey getSecretKey();

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    KeyPair getRsaKeyPair();

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    PrivateKey getPrivateKey();

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    PublicKey getPublicKey();
}
