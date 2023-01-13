package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.constant.SystemConst;
import cn.cpoet.clever.core.validator.CommValidatorRuleKeys;
import cn.cpoet.clever.core.validator.DynamicRuleType;
import cn.cpoet.clever.core.validator.ValidatorBean;
import cn.cpoet.yunzhi.note.comm.service.ISettingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class SettingServiceTest {
    @Autowired
    private ISettingService iSettingService;

    @Test
    void importValidatorTest() {
        ValidatorBean bean = new ValidatorBean();
        bean.setRule("^[1-9a-zA-Z_]{6,42}$");
        bean.setRuleType(DynamicRuleType.REGEX);
        bean.setMessage("账号由数字、字母及'_'组成，长度在6-42之间");
        iSettingService.setBean(CommValidatorRuleKeys.MEMBER_ACCOUNT, Collections.singleton(bean), SystemConst.SYS_ID);
    }
}