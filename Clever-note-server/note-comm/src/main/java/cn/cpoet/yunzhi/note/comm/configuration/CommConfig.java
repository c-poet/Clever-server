package cn.cpoet.yunzhi.note.comm.configuration;

import cn.cpoet.yunzhi.note.api.validator.ValidatorSettingAdapter;
import cn.cpoet.yunzhi.note.comm.support.DefaultValidatorSetting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CPoet
 */
@ComponentScan({"cn.cpoet.yunzhi.note.comm.service"})
public class CommConfig {
    @Bean
    @ConditionalOnMissingBean
    public ValidatorSettingAdapter validatorSettingAdapter() {
        return new DefaultValidatorSetting();
    }
}
