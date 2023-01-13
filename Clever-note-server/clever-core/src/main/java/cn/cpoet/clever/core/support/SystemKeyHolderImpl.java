package cn.cpoet.clever.core.support;

import cn.cpoet.clever.annotation.core.SystemKeyHolder;
import cn.cpoet.yunzhi.note.annotation.util.SecretUtil;
import cn.cpoet.clever.core.configuration.auto.SecretProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 密钥管理
 *
 * @author CPoet
 */
public class SystemKeyHolderImpl implements SystemKeyHolder, InitializingBean {

    @Autowired
    private SecretProperties secretProperties;

    private SecretKey sysSecret;
    private SecretKey secretKey;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Override
    public KeyPair getRsaKeyPair() {
        return new KeyPair(publicKey, privateKey);
    }

    @Override
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public boolean isSysSecret() {
        return Boolean.TRUE.equals(secretProperties.getEnableSysSecret());
    }

    @Override
    public SecretKey getSysKey() {
        return sysSecret;
    }

    @Override
    public SecretKey getSecretKey() {
        return secretKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        privateKey = SecretUtil.decodePrivateKey(secretProperties.getPrivateKey());
        publicKey = SecretUtil.decodePublicKey(secretProperties.getPublicKey());
        sysSecret = SecretUtil.decodeSecretKey(secretProperties.getSysKey());
        secretKey = SecretUtil.decodeSecretKey(secretProperties.getSecretKey());
    }
}
