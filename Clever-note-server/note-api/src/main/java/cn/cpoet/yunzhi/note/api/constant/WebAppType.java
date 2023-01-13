package cn.cpoet.yunzhi.note.api.constant;

import cn.cpoet.yunzhi.note.api.util.EnumUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.WebApplicationType;

/**
 * 网络应用类型定义
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum WebAppType {
    /**
     * Servlet应用
     */
    SERVLET(WebApplicationType.SERVLET),

    /**
     * Reactive应用
     */
    REACTIVE(WebApplicationType.REACTIVE),

    /**
     * 非网络应用
     */
    NONE(WebApplicationType.NONE);

    /**
     * 与Spring应用的类型映射
     */
    private final WebApplicationType type;

    /**
     * 根据类型获取
     *
     * @param type Spring应用类型
     * @return 映射类型
     */
    public static WebAppType ofType(WebApplicationType type) {
        return EnumUtil.valueSafeOf(values(), WebAppType::type, type).orElse(null);
    }
}
