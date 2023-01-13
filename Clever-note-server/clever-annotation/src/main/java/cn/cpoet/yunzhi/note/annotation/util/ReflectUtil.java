package cn.cpoet.yunzhi.note.annotation.util;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具
 * 依托{@link ReflectionUtils}的缓存能力
 *
 * @author wanggf
 */
public abstract class ReflectUtil {
    private ReflectUtil() {
    }

    /**
     * 获取类加载器
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader == null ? ReflectUtil.getClassLoader() : loader;
    }

    /**
     * 获取属性对象
     *
     * @param obj       待操作的对象
     * @param fieldName 属性名
     * @return 属性对象
     */
    public static Field getField(Object obj, String fieldName) {
        Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
        if (field != null && !field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 获取属性值
     *
     * @param obj       待操作的对象
     * @param fieldName 属性名
     * @return 属性值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        return ReflectionUtils.getField(getField(obj, fieldName), obj);
    }

    /**
     * 获取属性值
     *
     * @param obj       待操作的对象
     * @param fieldName 属性名
     * @param fieldType 属性类型class
     * @param <T>       属性类型
     * @return 属性值
     */
    public static <T> T getFieldValue(Object obj, String fieldName, Class<T> fieldType) {
        return compatibleCast2Str(getFieldValue(obj, fieldName), fieldType);
    }

    /**
     * 设置属性值
     *
     * @param obj       待操作的对象
     * @param fieldName 属性名
     * @param value     属性值
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        ReflectionUtils.setField(getField(obj, fieldName), obj, value);
    }

    /**
     * 获取方法对象
     *
     * @param obj        待操作的对象
     * @param methodName 方法名
     * @param paramTypes 参数类型列表
     * @return 方法对象
     */
    public static Method getMethod(Object obj, String methodName, Class<?>... paramTypes) {
        Method method = ReflectionUtils.findMethod(obj.getClass(), methodName, paramTypes);
        if (method != null && !method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 调用方法
     *
     * @param obj        待操作的对象
     * @param methodName 方法名
     * @param params     参数列表
     * @return 调用方法返回值
     */
    public static Object invokeMethod(Object obj, String methodName, Object... params) {
        Method method;
        if (params.length == 0) {
            method = getMethod(obj, methodName);
        } else {
            Class<?>[] paramTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass();
            }
            method = getMethod(obj, methodName, paramTypes);
        }
        return ReflectionUtils.invokeMethod(method, obj, params);
    }

    /**
     * 调用方法
     *
     * @param obj        待操作的对象
     * @param methodName 方法名
     * @param retType    返回值类型 class
     * @param params     参数列表
     * @param <T>        返回值类型
     * @return 调用方法返回值
     */
    public static <T> T invokeMethod(Object obj, String methodName, Class<T> retType, Object... params) {
        return compatibleCast2Str(invokeMethod(obj, methodName, params), retType);
    }

    /**
     * 兼容任意类型向{@link String}转换
     *
     * @param value 需要类型转换的值
     * @param tType 类型class
     * @param <T>   类型
     * @return 转换结果
     */
    public static <T> T compatibleCast2Str(Object value, Class<T> tType) {
        if (value == null) {
            return null;
        }
        if (tType.isAssignableFrom(String.class)) {
            return tType.cast(value instanceof String ? value : String.valueOf(value));
        }
        return tType.cast(value);
    }

    /**
     * 根据方法名称查找方法
     * <p>重载的情况下只返回查找到的第一个方法</p>
     *
     * @param methodName 方法名
     * @param methods    方法数组
     * @return 查找成功返回指定名称的方法，否则返回null
     */
    public static Method findMethodByName(String methodName, Method[] methods) {
        if (methods.length != 0) {
            if (methods.length == 1) {
                return methods[0].getName().equals(methodName) ? methods[0] : null;
            }
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 查找注解信息
     *
     * @param clazz       已知注解的class对象
     * @param annotations 注解
     * @param <T>         注解类型
     * @return 如果查找成功则返回注解信息，否则返回null
     */
    public static <T extends Annotation> T findAnnotation(Class<T> clazz, Annotation[] annotations) {
        if (annotations.length != 0) {
            if (annotations.length == 1) {
                return clazz.isAssignableFrom(annotations[0].getClass()) ? clazz.cast(annotations[0]) : null;
            }
            for (Annotation annotation : annotations) {
                if (clazz.isAssignableFrom(annotation.getClass())) {
                    return clazz.cast(annotation);
                }
            }
        }
        return null;
    }
}
