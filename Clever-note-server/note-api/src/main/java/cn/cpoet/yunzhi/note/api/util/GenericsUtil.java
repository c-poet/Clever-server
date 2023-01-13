package cn.cpoet.yunzhi.note.api.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型相关工具
 *
 * @author CPoet
 */
public abstract class GenericsUtil {
    private GenericsUtil() {
    }

    /**
     * 获取泛型实际类型
     *
     * @param clazz 泛型类型
     * @return 实际类型
     */
    public static Type getActualTypeArg(Class<?> clazz) {
        return ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获取泛型实际类型并转化成Class类型
     *
     * @param clazz 泛型类型
     * @param <T>   实际Class类型
     * @return 实际类型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getActualTypeArgClass(Class<?> clazz) {
        Type type = getActualTypeArg(clazz);
        return type instanceof Class ? (Class<T>) type : null;
    }
}
