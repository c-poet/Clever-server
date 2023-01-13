package cn.cpoet.yunzhi.note.domain.constant;

import cn.cpoet.yunzhi.note.api.exception.EnumUndefinedException;
import cn.cpoet.yunzhi.note.api.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.ebean.annotation.DbEnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 资源类型
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum PermissionType {
    /**
     * 操作权限
     * <p>例如：后端请求的API和其它笼统归类</p>
     */
    OPERATOR(1, "操作"),

    /**
     * 用于生成菜单树
     */
    MENU(1 << 1, "菜单"),

    /**
     * 前端页面路由
     */
    VIEW(1 << 2, "视图"),

    /**
     * 按钮级别的权限
     */
    BUTTON(1 << 3, "按钮"),

    /**
     * 事件类型
     */
    EVENT(1 << 4, "事件"),

    /**
     * 其它归类
     */
    OTHER(1 << 10, "其它");

    @Getter(onMethod_ = {@JsonValue, @DbEnumValue})
    private final int code;

    private final String desc;

    @JsonCreator
    public static PermissionType ofCode(int code) {
        return EnumUtil.valueSafeOf(values(), PermissionType::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
