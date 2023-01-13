package cn.cpoet.yunzhi.note.annotation.validator;

import javax.validation.ConstraintValidatorContext;

/**
 * @author CPoet
 */
public interface DynamicRuleValidator {
    /**
     * 是否支持
     *
     * @param ruleType 规则类型
     * @return bool
     */
    boolean support(DynamicRuleType ruleType);

    /**
     * 动态验证器
     *
     * @param val           需要验证的值
     * @param context       验证上下文
     * @param validatorBean 验证信息
     * @return 验证结果
     */
    boolean isValid(Object val, ConstraintValidatorContext context, ValidatorBean validatorBean);
}
