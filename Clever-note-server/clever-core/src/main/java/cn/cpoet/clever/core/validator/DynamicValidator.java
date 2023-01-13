package cn.cpoet.clever.core.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 动态验证
 *
 * @author Cpoet
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DynamicValidatorImpl.class)
@Documented
public @interface DynamicValidator {
    /**
     * 系统配置key
     *
     * @return 系统配置key
     */
    String value();

    /**
     * 规则来源类型
     *
     * @return 规则来源类型
     */
    UseRuleEnum useRule() default UseRuleEnum.SYS_MEMBER;

    String message() default "dynamic validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
