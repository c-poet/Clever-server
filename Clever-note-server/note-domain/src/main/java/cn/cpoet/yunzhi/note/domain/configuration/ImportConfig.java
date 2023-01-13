package cn.cpoet.yunzhi.note.domain.configuration;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.domain.configuration.auto.ImportProperties;
import cn.cpoet.yunzhi.note.domain.impord.ImportJsonScanner;
import cn.cpoet.yunzhi.note.domain.impord.ImportSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * 数据导入导出配置
 *
 * @author CPoet
 */
@Profile(value = {SystemConst.APP_PROFILE_DEV, SystemConst.APP_PROFILE_TEST})
public class ImportConfig {
    @Bean
    @ConfigurationProperties(prefix = "note.import")
    public ImportProperties importProperties() {
        return new ImportProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public ImportJsonScanner importJsonScanner() {
        return new ImportJsonScanner();
    }

    @Bean
    @ConditionalOnMissingBean
    public ImportSupport importSupport() {
        return new ImportSupport();
    }
}
