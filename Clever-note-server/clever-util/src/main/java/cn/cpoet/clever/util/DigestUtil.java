package cn.cpoet.clever.util;

import java.security.MessageDigest;

/**
 * md5工具
 *
 * @author CPoet
 */
public abstract class DigestUtil {
    private DigestUtil() {
    }

    public static String md5hex(String str) {
        return binary2hex(md5binary(str));
    }

    public static String md5hex16(String str) {
        return interceptMd5(md5hex(str));
    }

    public static String md5hex(byte[] bytes) {
        return binary2hex(md5binary(bytes));
    }

    public static String md5hex16(byte[] bytes) {
        return interceptMd5(md5hex(bytes));
    }

    public static byte[] md5binary(String str) {
        return md5binary(str == null ? "null".getBytes() : str.getBytes());
    }

    public static byte[] md5binary(byte[] bytes) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return md5.digest(bytes);
    }

    public static String binary2hex(byte[] bytes) {
        StringBuilder md5str = new StringBuilder();
        int digital;
        for (byte bit : bytes) {
            digital = bit;
            if (digital < 0) {
                digital &= 0xFF;
            }
            if (digital < 0x10) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }

    /**
     * 截取16位md5
     *
     * @param str md5值
     * @return 16位
     */
    public static String interceptMd5(String str) {
        return str == null || str.length() < 24 ? str : str.substring(8, 24);
    }
}
