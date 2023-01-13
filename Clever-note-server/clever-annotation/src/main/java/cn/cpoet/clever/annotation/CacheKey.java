package cn.cpoet.clever.annotation;

import cn.cpoet.clever.constant.ElementExpEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存Key
 *
 * @author CPoet
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheKey {
    /**
     * 表达式
     *
     * @return 表达式
     */
    String value();

    /**
     * 是否包含该参数
     *
     * @return 是否包含该参数
     */
    boolean required() default true;

    /**
     * 使用的El表达式
     *
     * @return El表达式
     */
    ElementExpEnum elExp() default ElementExpEnum.SIMPLE;
}
