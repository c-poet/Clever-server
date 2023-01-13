package cn.cpoet.yunzhi.note.auth.interceptor;

import cn.cpoet.yunzhi.note.api.core.AppContext;
import cn.cpoet.yunzhi.note.api.core.RequestWrapper;
import cn.cpoet.yunzhi.note.auth.configuration.auto.AuthTokenProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Feign认证请求拦截器
 *
 * @author CPoet
 */
public class AuthenticateFeignInterceptor implements RequestInterceptor {

    @Autowired
    private AppContext appContext;

    @Autowired
    private AuthTokenProperties authTokenProperties;

    @Override
    public void apply(RequestTemplate template) {
        RequestWrapper requestWrapper = appContext.getRequestWrapper();
        if (requestWrapper != null) {
            String tokenHeader = authTokenProperties.getTokenHeader();
            String header = requestWrapper.getHeader(tokenHeader);
            if (StringUtils.hasText(header)) {
                template.header(tokenHeader, header);
            }
            String tokenParams = authTokenProperties.getTokenParam();
            String parameter = requestWrapper.getParameter(tokenParams);
            if (StringUtils.hasText(parameter)) {
                template.query(tokenParams, parameter);
            }
        }
    }
}
