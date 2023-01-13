package cn.cpoet.clever.core.validator;


import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 动态验证
 *
 * @author Cpoet
 */
@Slf4j
public class DynamicValidatorImpl implements ConstraintValidator<DynamicValidator, Object> {

    private DynamicValidator dynamicValidator;
    private List<DynamicRuleValidator> ruleValidators;

    @Override
    public void initialize(DynamicValidator constraintAnnotation) {
        this.dynamicValidator = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String settingKey = dynamicValidator.value();
        if (!StringUtils.hasText(settingKey)) {
            return true;
        }
        ValidatorSettingAdapter settingAdapter = AppContextUtil.getBean(ValidatorSettingAdapter.class);
        Assert.notNull(settingAdapter, "Configuration adapter not implemented");
        List<ValidatorBean> validatorBeans = settingAdapter.byKey(settingKey, dynamicValidator.useRule());
        if (CollectionUtils.isEmpty(validatorBeans)) {
            log.debug("未配置名称是{}的验证规则", settingKey);
            return true;
        }
        return doValid(value, context, validatorBeans);
    }

    private boolean doValid(Object value, ConstraintValidatorContext context, List<ValidatorBean> validatorBeans) {
        // 获取所有动态验证器
        List<DynamicRuleValidator> ruleValidators = getRuleValidators();
        for (ValidatorBean validatorBean : validatorBeans) {
            if (!handleValid(value, context, validatorBean, ruleValidators)) {
                // 自定义消息模板
                String message = validatorBean.getMessage();
                if (StringUtils.hasText(message)) {
                    // 关闭默认的提示信息
                    context.disableDefaultConstraintViolation();
                    context
                        .buildConstraintViolationWithTemplate(message)
                        .addConstraintViolation();
                }
                return false;
            }
        }
        return true;
    }

    private boolean handleValid(Object value,
                                ConstraintValidatorContext context,
                                ValidatorBean validatorBean,
                                List<DynamicRuleValidator> ruleValidators) {
        for (DynamicRuleValidator ruleValidator : ruleValidators) {
            if (ruleValidator.support(validatorBean.getRuleType())) {
                if (!ruleValidator.isValid(value, context, validatorBean)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<DynamicRuleValidator> getRuleValidators() {
        if (ruleValidators == null) {
            ruleValidators = new ArrayList<>();
            // 添加默认验证器
            ruleValidators.add(new DynamicRuleRegexValidator());
            ruleValidators.add(new DynamicRuleSpelValidator());
            // 添加扩展验证器
            List<DynamicRuleValidator> customRuleValidator = findRuleValidator();
            if (!CollectionUtils.isEmpty(customRuleValidator)) {
                ruleValidators.addAll(customRuleValidator);
            }
        }
        return ruleValidators;
    }

    private List<DynamicRuleValidator> findRuleValidator() {
        if (AppContextUtil.appContext() != null) {
            ApplicationContext applicationContext = AppContextUtil.appContext().getApplicationContext();
            Map<String, DynamicRuleValidator> map = applicationContext.getBeansOfType(DynamicRuleValidator.class);
            if (CollectionUtils.isEmpty(map)) {
                return Collections.emptyList();
            }
            return new ArrayList<>(map.values());
        }
        return Collections.emptyList();
    }
}
