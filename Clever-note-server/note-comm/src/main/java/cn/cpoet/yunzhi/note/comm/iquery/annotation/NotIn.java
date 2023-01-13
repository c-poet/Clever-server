package cn.cpoet.yunzhi.note.comm.iquery.annotation;


import java.lang.annotation.*;

/**
 * @author CPoet
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotIn {
    /**
     * 属性名
     *
     * @return 属性名
     */
    String value() default "";
}
