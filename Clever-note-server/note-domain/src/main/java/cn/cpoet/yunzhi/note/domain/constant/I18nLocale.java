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
 * 国际化语言环境
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum I18nLocale {
    /**
     * 美国英文
     */
    EN_US(0, "en-US"),

    /**
     * 中国大陆
     */
    ZH_CN(1, "zh-CN"),

    /**
     * 中国台湾
     */
    ZH_TW(2, "zh-TW");

    @Getter(onMethod_ = {@JsonValue, @DbEnumValue})
    private final int code;
    private final String desc;

    @JsonCreator
    public static I18nLocale ofCode(int code) {
        return EnumUtil.valueSafeOf(values(), I18nLocale::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
