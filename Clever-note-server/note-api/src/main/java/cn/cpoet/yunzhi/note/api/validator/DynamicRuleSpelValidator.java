package cn.cpoet.yunzhi.note.api.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidatorContext;

/**
 * SPEL表达式
 *
 * @author CPoet
 */
@Slf4j
public class DynamicRuleSpelValidator implements DynamicRuleValidator {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Override
    public boolean support(DynamicRuleType ruleType) {
        return DynamicRuleType.SPEL.equals(ruleType);
    }

    @Override
    public boolean isValid(Object val, ConstraintValidatorContext context, ValidatorBean validatorBean) {
        String rule = validatorBean.getRule();
        if (!StringUtils.hasText(rule)) {
            log.debug("未配置验证规则");
            return true;
        }
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("val", val);
        return Boolean.TRUE.equals(parser.parseExpression(validatorBean.getRule()).getValue(evaluationContext, Boolean.class));
    }
}
