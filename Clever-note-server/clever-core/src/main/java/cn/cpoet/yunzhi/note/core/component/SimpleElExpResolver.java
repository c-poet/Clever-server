package cn.cpoet.yunzhi.note.core.component;

import cn.cpoet.yunzhi.note.annotation.util.ReflectUtil;
import cn.cpoet.yunzhi.note.annotation.constant.ElementExpEnum;
import org.springframework.stereotype.Component;

/**
 * 简单属性解析器实现
 *
 * @author wanggf
 */
@Component
public class SimpleElExpResolver implements ElExpResolver {

    private final static String FIELD_NAME_SEPARATION = "\\.";

    @Override
    public Object parse(Object target, String exp) {
        if (target == null || exp == null || exp.isEmpty()) {
            return null;
        }
        String[] names = exp.split(FIELD_NAME_SEPARATION);
        if (names.length == 1) {
            return ReflectUtil.getFieldValue(target, names[0]);
        }
        Object ret = target;
        for (String name : names) {
            ret = ReflectUtil.getField(ret, name);
        }
        return ret;
    }

    @Override
    public ElementExpEnum getType() {
        return ElementExpEnum.SIMPLE;
    }

}
