package cn.cpoet.yunzhi.note.api.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * 枚举工具
 *
 * @author wanggf
 */
public abstract class EnumUtil {
    private EnumUtil() {
    }

    /**
     * 根据匹配条件获取枚举值
     *
     * @param values 查找列表
     * @param getter 获取特征值的方法
     * @param val    目标值
     * @param <T>    枚举类型
     * @return 合符条件的枚举值
     */
    public static <T extends Enum<T>> T valueOf(T[] values, Function<T, Object> getter, Object val) {
        if (values != null && values.length > 0) {
            for (T value : values) {
                if (Objects.equals(getter.apply(value), val)) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * 安全的返回枚举值
     *
     * @param values 查找列表
     * @param getter 获取特征值的方法
     * @param val    目标值
     * @param <T>    枚举类型
     * @return 合符条件的枚举值
     */
    public static <T extends Enum<T>> Optional<T> valueSafeOf(T[] values, Function<T, Object> getter, Object val) {
        return Optional.ofNullable(valueOf(values, getter, val));
    }
}
