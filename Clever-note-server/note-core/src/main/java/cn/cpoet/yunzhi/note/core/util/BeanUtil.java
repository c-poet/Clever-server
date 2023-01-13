package cn.cpoet.yunzhi.note.core.util;

import org.springframework.beans.BeanUtils;

/**
 * bean工具
 * <p>基于{@link BeanUtils}的二次封装和扩展</p>
 *
 * @author CPoet
 */
public abstract class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 属性拷贝
     *
     * @see BeanUtils#copyProperties(Object, Object)
     */
    public static <T> T copyProperties(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
