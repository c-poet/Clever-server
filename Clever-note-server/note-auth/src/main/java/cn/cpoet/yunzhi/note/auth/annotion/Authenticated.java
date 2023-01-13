package cn.cpoet.yunzhi.note.auth.annotion;

import cn.cpoet.yunzhi.note.api.constant.SubjectType;

import java.lang.annotation.*;

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
