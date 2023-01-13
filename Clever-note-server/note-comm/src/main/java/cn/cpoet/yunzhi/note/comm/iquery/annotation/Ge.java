package cn.cpoet.yunzhi.note.comm.iquery.annotation;

import java.lang.annotation.*;

/**
 * 大于等于
 *
 * @author CPoet
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ge {
    /**
     * 属性名
     *
     * @return 属性名
     */
    String value() default "";
}
