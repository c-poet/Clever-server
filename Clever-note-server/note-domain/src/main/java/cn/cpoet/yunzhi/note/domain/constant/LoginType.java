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
 * 登录方式
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum LoginType {
    /**
     * 账号密码
     */
    ACCOUNT("account", "账号密码登录"),

    /**
     * 手机号验证登录
     */
    MOBILE("mobile", "手机登录"),

    /**
     * 邮箱登录
     */
    EMAIL("email", "邮箱登录"),

    /**
     * QQ认证授权
     */
    QQ_AUTH("qq-auth", "QQ授权"),

    /**
     * 微信认证授权
     */
    WECHAT("wechat", "微信授权"),

    /**
     * 微信小程序
     */
    WECHAT_MINI_APP("wechat-mini-app", "微信小程序"),

    /**
     * Github认证授权
     */
    GITHUB("github", "github授权"),

    /**
     * gitee认证授权
     */
    GITEE("gitee", "gitee授权"),

    /**
     * 百度认证授权
     */
    BAIDU("baidu", "百度授权");

    @Getter(onMethod_ = {@DbEnumValue, @JsonValue})
    private final String code;
    private final String desc;

    @JsonCreator
    public static LoginType ofCode(String code) {
        return EnumUtil.valueSafeOf(values(), LoginType::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
