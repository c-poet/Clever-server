package cn.cpoet.clever.auth.annotion;

import cn.cpoet.clever.constant.LogicEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否具有角色
 *
 * @author CPoet
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {
    String[] value();

    LogicEnum logic() default LogicEnum.OR;
}
