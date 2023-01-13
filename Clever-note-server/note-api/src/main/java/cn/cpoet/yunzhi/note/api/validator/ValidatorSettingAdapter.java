package cn.cpoet.yunzhi.note.api.validator;

import java.util.List;

/**
 * 验证器配置
 *
 * @author CPoet
 */
public interface ValidatorSettingAdapter {
    /**
     * 获取验证配置
     *
     * @param settingKey 配置key
     * @param useRule    规则来源类型
     * @return 验证配置信息
     */
    List<ValidatorBean> byKey(String settingKey, UseRuleEnum useRule);
}
