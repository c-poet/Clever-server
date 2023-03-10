package cn.cpoet.clever.annotation;


import cn.cpoet.clever.constant.ActionTypes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志
 *
 * @author CPoet
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionLog {
    /**
     * 操作标题
     *
     * @return 操作标题
     */
    String title() default "";

    /**
     * 操作详情，支持SPEL表达式
     *
     * @return 操作详情
     */
    String description() default "";

    /**
     * 方法返回值在SPEL中的变量名
     *
     * @return 返回值变量名
     */
    String resultVar() default "$ret";

    /**
     * 异常信息在SPEL中的变量名
     *
     * @return 异常信息变量名
     */
    String throwableVar() default "$throwable";

    /**
     * 操作类型
     *
     * @return 操作类型
     */
    ActionTypes action() default ActionTypes.UPDATE;
}
