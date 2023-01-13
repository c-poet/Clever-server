package cn.cpoet.yunzhi.note.api.validator;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;

/**
 * 公共验证规则配置key
 *
 * @author CPoet
 */
public interface CommValidatorRuleKeys {
    /**
     * 用户账号
     */
    String MEMBER_ACCOUNT = SystemConst.SETTING_SYS_PREFIX + "memberAccountValidatorRule";

    /**
     * 用户密码
     */
    String MEMBER_PASSWORD = SystemConst.SETTING_SYS_PREFIX + "memberPasswordValidatorRule";
}
