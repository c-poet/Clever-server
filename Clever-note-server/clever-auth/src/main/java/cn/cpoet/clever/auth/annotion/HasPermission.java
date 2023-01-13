package cn.cpoet.clever.auth.annotion;

import cn.cpoet.clever.constant.LogicEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否具有权限
 *
 * @author CPoet
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasPermission {
    String[] value();

    LogicEnum logic() default LogicEnum.OR;
}
