package cn.cpoet.yunzhi.note.auth.annotion;

import cn.cpoet.yunzhi.note.api.constant.LogicEnum;

import java.lang.annotation.*;

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
