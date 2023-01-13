package cn.cpoet.clever.core.filter;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.clever.annotation.core.AppContext;
import cn.cpoet.clever.annotation.core.ReqsTimeHolder;
import cn.cpoet.clever.annotation.core.TraceInfo;
import cn.cpoet.clever.core.support.ServletRequestWrapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author CPoet
 */
public class TraceWebFilter implements OrderedFilter {
    @Autowired
    private AppContext appContext;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 记录请求开始时间
        ReqsTimeHolder.start();
        // 获取链路信息并存储至MDC中
        TraceInfo traceInfo = appContext.getTraceInfo(ServletRequestWrapper.wrapper((HttpServletRequest) request));
        MDC.put(SystemConst.SPAN_ID, String.valueOf(traceInfo.getSpanId()));
        MDC.put(SystemConst.TRACE_ID, traceInfo.getTraceId());
        chain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
