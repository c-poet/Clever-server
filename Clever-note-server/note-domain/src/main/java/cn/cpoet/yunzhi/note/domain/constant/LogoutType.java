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
 * 登出类型
 * <p>由于系统采用Jwt保存登录状态，这种无状态的情况下，登出信息参考价值不高</p>
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum LogoutType {
    /**
     * 自动退出，身份认证到期等
     */
    AUTO(1, "自动退出"),

    /**
     * 管理员强制
     */
    FORCE(2, "强制退出"),

    /**
     * 用户自行退出
     */
    MANUAL(3, "用户操作");

    @Getter(onMethod_ = {@DbEnumValue, @JsonValue})
    private final int code;
    private final String desc;

    @JsonCreator
    public static LogoutType ofCode(int code) {
        return EnumUtil.valueSafeOf(values(), LogoutType::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
