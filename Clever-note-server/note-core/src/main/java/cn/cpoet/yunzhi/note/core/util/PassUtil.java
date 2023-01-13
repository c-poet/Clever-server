package cn.cpoet.yunzhi.note.core.util;

import cn.cpoet.yunzhi.note.api.util.DigestUtil;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * 密码工具
 *
 * @author wanggf
 */
public abstract class PassUtil {
    /**
     * 产生随机的盐(32bit)
     *
     * @return 32位长度的盐
     */
    public static String randomSalt() {
        return UUIDUtil.randomPure();
    }

    /**
     * 密码校验
     *
     * @param target 待验证的密码
     * @param salt   加密用的盐
     * @param origin 已加密过的密码
     * @return bool
     */
    public static boolean verify(String target, String salt, String origin) {
        return Objects.equals(encrypt(target, salt), origin);
    }

    /**
     * 计算密码摘要
     *
     * @param password 密码
     * @param salt     混淆字符串
     * @return 密码摘要
     */
    public static String encrypt(String password, String salt) {
        return encrypt(password, salt, 1);
    }

    /**
     * 计算密码摘要
     *
     * @param password 密码
     * @param salt     混淆字符串
     * @param num      混淆次数
     * @return 密码摘要
     */
    public static String encrypt(String password, String salt, int num) {
        if (password == null) {
            throw new InvalidParameterException("必须传入需要计算摘要的密码");
        }
        salt = salt == null ? "" : salt;
        while (num-- > 0) {
            password = DigestUtil.md5hex(merge(password, salt));
        }
        return password;
    }

    /**
     * 字符串混合
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 混合结果
     */
    private static String merge(String str1, String str2) {
        byte[] bytes1 = str1.getBytes();
        byte[] bytes2 = str2.getBytes();
        StringBuilder builder = new StringBuilder(bytes1.length + bytes2.length);
        int len = Math.min(bytes1.length, bytes2.length);
        for (int i = 0; i < len; ++i) {
            builder.append(bytes1[i]).append(bytes2[i]);
        }
        bytes1 = bytes1.length == len ? bytes2 : bytes1;
        while (len < bytes1.length) {
            builder.append(bytes1[len++]);
        }
        return builder.toString();
    }

    private PassUtil() {
    }
}
