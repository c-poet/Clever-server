package cn.cpoet.yunzhi.note.comm.iquery.annotation;

import java.lang.annotation.*;

/**
 * In
 *
 * @author CPoet
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface In {
    /**
     * 属性名
     *
     * @return 属性名
     */
    String value() default "";
}
