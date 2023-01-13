package cn.cpoet.yunzhi.note.core.component;

import cn.cpoet.yunzhi.note.annotation.constant.ElementExpEnum;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

/**
 * @author wanggf
 */
@Component
public class SpelElExpResolver implements ElExpResolver {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Override
    public Object parse(Object target, String exp) {
        if (target == null || exp == null || exp.isEmpty()) {
            return null;
        }
        return parser.parseExpression(exp).getValue(target);
    }

    @Override
    public ElementExpEnum getType() {
        return ElementExpEnum.SPEL;
    }

}
