package cn.cpoet.yunzhi.note.comm.iquery.annotation;

import cn.cpoet.yunzhi.note.comm.iquery.constant.LikeWays;

import java.lang.annotation.*;

/**
 * 模糊查询
 *
 * @author CPoet
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Like {
    /**
     * 属性名
     *
     * @return 属性名
     */
    String value() default "";

    /**
     * 匹配方式
     *
     * @return 匹配方式
     */
    LikeWays way() default LikeWays.CONTAIN;
}
