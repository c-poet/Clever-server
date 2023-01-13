package cn.cpoet.yunzhi.note.api.cache;

import cn.cpoet.yunzhi.note.api.constant.ElementExpEnum;

import java.lang.annotation.*;

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
