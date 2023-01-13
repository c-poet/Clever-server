package cn.cpoet.yunzhi.note.api.util;

import cn.cpoet.yunzhi.note.api.core.SystemKeyHolder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 加密工具
 *
 * @author CPoet
 */
public abstract class SecretUtil {
    public final static String ALGORITHM_RSA = "RSA";

    public final static String ALGORITHM_DES = "DES";

    private SecretUtil() {
    }

    public static KeyPair genKeyPair() throws GeneralSecurityException {
        return KeyPairGenerator.getInstance(ALGORITHM_RSA).generateKeyPair();
    }

    public static SecretKey genSecretKey() throws GeneralSecurityException {
        return KeyGenerator.getInstance(ALGORITHM_DES).generateKey();
    }

    public static String encodeKey(Key key) {
        return toBase64(key.getEncoded());
    }

    public static PrivateKey decodePrivateKey(String privateKey) throws GeneralSecurityException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ofBase64(privateKey));
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePrivate(pkcs8EncodedKeySpec);
    }

    public static PublicKey decodePublicKey(String publicKey) throws GeneralSecurityException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ofBase64(publicKey));
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(x509EncodedKeySpec);
    }

    public static SecretKey decodeSecretKey(String secretKey) {
        return new SecretKeySpec(ofBase64(secretKey), ALGORITHM_DES);
    }

    public static byte[] encrypt(PublicKey publicKey, byte[] plaintext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    public static String encrypt2base64(PublicKey publicKey, byte[] plaintext) throws GeneralSecurityException {
        return toBase64(encrypt(publicKey, plaintext));
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] ciphertext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    public static byte[] decrypt4base64(PrivateKey privateKey, String ciphertext) throws GeneralSecurityException {
        return decrypt(privateKey, ofBase64(ciphertext));
    }

    public static byte[] encrypt(SecretKey secretKey, byte[] plaintext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext);
    }

    public static String encrypt2base64(SecretKey secretKey, byte[] plaintext) throws GeneralSecurityException {
        return toBase64(encrypt(secretKey, plaintext));
    }

    public static byte[] decrypt(SecretKey secretKey, byte[] ciphertext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(ciphertext);
    }

    public static byte[] decrypt4base64(SecretKey secretKey, String ciphertext) throws GeneralSecurityException {
        return decrypt(secretKey, ofBase64(ciphertext));
    }

    public static String toBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] ofBase64(String data) {
        return Base64.getDecoder().decode(data);
    }


    public static void main(String[] args) throws GeneralSecurityException {
        String username = "root";
        String password = "123456";

        SecretKey secretKey = decodeSecretKey(SystemKeyHolder.DEFAULT_SECRET_KEY);

        System.out.println(encrypt2base64(secretKey, username.getBytes(StandardCharsets.UTF_8)));
        System.out.println();
        System.out.println(encrypt2base64(secretKey, password.getBytes(StandardCharsets.UTF_8)));
    }
}
