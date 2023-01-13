package cn.cpoet.yunzhi.note.core.util;

import java.util.UUID;

/**
 * uuid工具
 *
 * @author CPoet
 */
public abstract class UUIDUtil {
    /**
     * 产生随机uuid，带“-”
     *
     * @return 随机uuid
     */
    public static String random() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生随机uuid，不带"-"
     *
     * @return 随机uuid
     */
    public static String randomPure() {
        return random().replaceAll("-", "");
    }

    private UUIDUtil() {
    }
}
