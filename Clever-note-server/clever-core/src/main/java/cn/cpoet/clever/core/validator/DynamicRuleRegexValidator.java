package cn.cpoet.clever.core.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 正式表达式
 *
 * @author CPoet
 */
@Slf4j
public class DynamicRuleRegexValidator implements DynamicRuleValidator {
    @Override
    public boolean support(DynamicRuleType ruleType) {
        return DynamicRuleType.REGEX.equals(ruleType);
    }

    @Override
    public boolean isValid(Object val, ConstraintValidatorContext context, ValidatorBean validatorBean) {
        String rule = validatorBean.getRule();
        if (!StringUtils.hasText(rule)) {
            log.debug("配置的验证规则为空.");
            return true;
        }
        return Pattern.matches(rule, String.valueOf(val));
    }
}
