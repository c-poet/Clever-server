package cn.cpoet.clever.core.support;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.clever.annotation.core.AppContext;
import cn.cpoet.clever.annotation.core.SystemKeyHolder;
import cn.cpoet.clever.annotation.core.TraceInfo;
import cn.cpoet.yunzhi.note.annotation.util.JsonUtil;
import cn.cpoet.yunzhi.note.annotation.util.SecretUtil;
import cn.cpoet.clever.core.constant.FeignConst;
import cn.cpoet.clever.core.exception.FeignException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;

/**
 * Feign全局数据加密、特征标识
 *
 * @author CPoet
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private AppContext appContext;

    @Autowired
    private SystemKeyHolder systemKeyHolder;

    @Override
    public void apply(RequestTemplate template) {
        FeignAuthBean feignAuthBean = new FeignAuthBean();
        feignAuthBean.setClient(applicationName);
        feignAuthBean.setTimeMillis(System.currentTimeMillis());
        // 增加约定的Feign调用标识
        try {
            String bean = JsonUtil.writeValueAsString(feignAuthBean);
            template.header(FeignConst.FEIGN_FLAG, SecretUtil.encrypt2base64(systemKeyHolder.getPublicKey(),
                bean.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new FeignException("发起Feign调用前，设置约定标识失败.", e);
        }
        template.header(FeignConst.FEIGN_CLIENT, applicationName);
        // 添加链路信息
        TraceInfo traceInfo = appContext.getTraceInfo();
        template.header(SystemConst.SPAN_ID, String.valueOf(traceInfo.getSpanId()));
        template.header(SystemConst.TRACE_ID, traceInfo.getTraceId());
    }
}
