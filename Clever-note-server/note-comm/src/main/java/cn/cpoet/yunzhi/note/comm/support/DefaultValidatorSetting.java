package cn.cpoet.yunzhi.note.comm.support;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.api.constant.SubjectType;
import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.api.util.AppContextUtil;
import cn.cpoet.yunzhi.note.api.validator.UseRuleEnum;
import cn.cpoet.yunzhi.note.api.validator.ValidatorBean;
import cn.cpoet.yunzhi.note.api.validator.ValidatorSettingAdapter;
import cn.cpoet.yunzhi.note.comm.service.ISettingService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 验证器配置默认实现
 *
 * @author CPoet
 */
public class DefaultValidatorSetting implements ValidatorSettingAdapter {

    private ISettingService iSettingService;

    @Override
    public List<ValidatorBean> byKey(String settingKey, UseRuleEnum useRule) {
        if (iSettingService == null) {
            iSettingService = AppContextUtil.getBean(ISettingService.class);
            Assert.notNull(iSettingService, "ISettingService instance not found");
        }
        if (!UseRuleEnum.SYSTEM.equals(useRule)) {
            Subject subject = AppContextUtil.authContext().getSubject();
            // 非认证的用户
            if (SubjectType.STAFF.equals(subject.getType())) {
                List<ValidatorBean> validatorBeans = byKeyAndMemberId(settingKey, subject.getUid());
                if (UseRuleEnum.MEMBER.equals(useRule) || !CollectionUtils.isEmpty(validatorBeans)) {
                    return validatorBeans;
                }
            }
        }
        return byKeyAndMemberId(settingKey, SystemConst.SYS_ID);
    }

    private List<ValidatorBean> byKeyAndMemberId(String settingKey, Long memberId) {
        return iSettingService.getBean(settingKey, memberId, new TypeReference<List<ValidatorBean>>() {
        });
    }
}
