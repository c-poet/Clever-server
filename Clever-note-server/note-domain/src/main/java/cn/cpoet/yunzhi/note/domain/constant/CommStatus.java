package cn.cpoet.yunzhi.note.domain.constant;

import cn.cpoet.yunzhi.note.annotation.exception.EnumUndefinedException;
import cn.cpoet.yunzhi.note.annotation.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.ebean.annotation.DbEnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 常用状态
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum CommStatus {
    /**
     * 禁用状态
     */
    DISABLED(0, "禁用"),

    /**
     * 启用状态
     */
    ENABLED(1, "启用"),

    /**
     * 状态未设定
     */
    NONE(2, "未设定");

    @Getter(onMethod_ = {@DbEnumValue, @JsonValue})
    private final int code;

    private final String desc;

    @JsonCreator
    public static CommStatus ofCode(int code) {
        return EnumUtil.valueSafeOf(values(), CommStatus::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
