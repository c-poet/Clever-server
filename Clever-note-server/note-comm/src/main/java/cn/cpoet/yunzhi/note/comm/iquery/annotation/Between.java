package cn.cpoet.yunzhi.note.comm.iquery.annotation;

import java.lang.annotation.*;

/**
 * 范围
 *
 * @author CPoet
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Between {
    /**
     * 属性名
     *
     * @return 属性名
     */
    String value() default "";

    /**
     * 是否是左区间
     *
     * @return 是否是左区间
     */
    boolean left() default true;

    /**
     * 后缀
     * <p>当设置{@link #value()}属性名时该值无效</p>
     *
     * @return 后缀
     */
    String suffix() default "";
}
