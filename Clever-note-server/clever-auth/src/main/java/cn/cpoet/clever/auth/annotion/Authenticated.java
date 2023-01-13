package cn.cpoet.clever.auth.annotion;

import cn.cpoet.clever.constant.SubjectType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证
 *
 * @author CPoet
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticated {
    /**
     * 可以访问的主体类型
     *
     * @return 主体类型
     */
    SubjectType[] subjects() default SubjectType.STAFF;

    /**
     * 自定义提示信息
     *
     * @return 自定义信息
     */
    String message() default "";
}
