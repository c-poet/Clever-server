package cn.cpoet.yunzhi.note.core.configuration;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.yunzhi.note.annotation.core.AppContext;
import cn.cpoet.yunzhi.note.annotation.feign.FeignTarget;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author CPoet
 */
@Profile({SystemConst.APP_PROFILE_DEV, SystemConst.APP_PROFILE_TEST})
public class OpenApiConfig {
    static {
        SpringDocUtils
            .getConfig()
            .addRequestWrapperToIgnore(Subject.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openApiInfo(AppContext appContext) {
        return new OpenAPI()
            .info(new Info()
                .title(appContext.appInfo().name())
                .version(appContext.appInfo().version().visible())
                .contact(new Contact()
                    .name(appContext.appInfo().author())
                    .email(appContext.appInfo().email())
                    .url(appContext.appInfo().site())));
    }

    @Bean
    @ConditionalOnMissingBean(name = "openApi")
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
            .group("open")
            .packagesToScan("cn.cpoet")
            .addOpenApiMethodFilter(method -> !method.isAnnotationPresent(FeignTarget.class))
            .build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "feignOpenApi")
    public GroupedOpenApi feignOpenApi() {
        return GroupedOpenApi.builder()
            .group("feign")
            .packagesToScan("cn.cpoet")
            .addOpenApiMethodFilter(method -> method.isAnnotationPresent(FeignTarget.class))
            .build();
    }
}
