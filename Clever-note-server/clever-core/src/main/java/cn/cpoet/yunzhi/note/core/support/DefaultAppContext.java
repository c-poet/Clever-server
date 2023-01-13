package cn.cpoet.yunzhi.note.core.support;

import cn.cpoet.yunzhi.note.annotation.auth.AuthContext;
import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.yunzhi.note.annotation.constant.WebAppType;
import cn.cpoet.yunzhi.note.annotation.core.AppContext;
import cn.cpoet.yunzhi.note.annotation.core.AppInfo;
import cn.cpoet.yunzhi.note.annotation.core.RequestWrapper;
import cn.cpoet.yunzhi.note.annotation.core.TraceInfo;
import cn.cpoet.yunzhi.note.core.util.UUIDUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * 默认应用上下文
 *
 * @author CPoet
 */
@SuppressWarnings("all")
public class DefaultAppContext implements AppContext, ApplicationContextAware {

    /**
     * 链路信息缓存
     */
    private final static String TRACE_INFO_CACHE_KEY = "$$traceInfoCache";

    /**
     * Servlet上下文全限定类名
     */
    private final static String SERLVET_APPLICATION_CONTEXT = "org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext";

    /**
     * Reactive上下文全限定类名
     */
    private final static String REACTIVE_APPLICATION_CONTECT = "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";

    private AuthContext authContext;
    private RequestWrapper globalRequestWrapper;
    private ConversionService conversionService;
    private ApplicationContext applicationContext;

    @Override
    public AppInfo appInfo() {
        return AppInfo.INSTANCE;
    }

    @Override
    public String getSpName() {
        return getApplicationContext().getApplicationName();
    }

    @Override
    public TraceInfo getTraceInfo() {
        RequestWrapper requestWrapper = getRequestWrapper();
        if (requestWrapper == null) {
            return TraceInfoBean.of(SystemConst.DEFAULT_SPAN_ID, UUIDUtil.randomPure());
        }
        return getTraceInfo(requestWrapper);
    }

    @Override
    public TraceInfo getTraceInfo(RequestWrapper request) {
        Object attr = request.getAttribute(TRACE_INFO_CACHE_KEY);
        if (attr instanceof TraceInfo) {
            return (TraceInfo) attr;
        }
        String tranceId = request.getHeader(SystemConst.TRACE_ID);
        // 链路id不存在则生成新的id
        if (!StringUtils.hasText(tranceId)) {
            tranceId = UUIDUtil.randomPure();
        }
        String spanId = request.getHeader(SystemConst.SPAN_ID);
        int nextSpanId = SystemConst.DEFAULT_SPAN_ID;
        try {
            if (StringUtils.hasText(spanId)) {
                nextSpanId = Integer.parseInt(spanId) + 1;
            }
        } catch (NumberFormatException ignored) {
        }
        TraceInfoBean traceInfoBean = TraceInfoBean.of(nextSpanId, tranceId);
        request.setAttribute(TRACE_INFO_CACHE_KEY, traceInfoBean);
        return traceInfoBean;
    }

    @Override
    public AuthContext authContext() {
        return authContext == null
            ? authContext = getBean(AuthContext.class)
            : authContext;
    }

    @Override
    public WebAppType getAppType() {
        ApplicationContext applicationContext = getApplicationContext();
        ClassLoader classLoader = applicationContext.getClassLoader();
        try {
            Class<?> servletClass = ClassUtils.forName(SERLVET_APPLICATION_CONTEXT, classLoader);
            if (servletClass.isAssignableFrom(applicationContext.getClass())) {
                return WebAppType.SERVLET;
            }
        } catch (Exception ignored) {
        }
        try {
            Class<?> reactiveClass = ClassUtils.forName(REACTIVE_APPLICATION_CONTECT, classLoader);
            if (reactiveClass.isAssignableFrom(applicationContext.getClass())) {
                return WebAppType.REACTIVE;
            }
        } catch (Exception ignored) {
        }
        return WebAppType.NONE;
    }

    @Override
    public RequestWrapper getRequestWrapper() {
        try {
            return globalRequestWrapper == null
                ? globalRequestWrapper = getBean(RequestWrapper.class)
                : globalRequestWrapper;
        } catch (Exception ignored) {
        }
        return globalRequestWrapper;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public Environment getEnvironment() {
        return getApplicationContext().getEnvironment();
    }

    @Override
    public ConversionService conversionService() {
        return conversionService == null
            ? conversionService = getBean(ConversionService.class)
            : conversionService;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        try {
            return getApplicationContext().getBean(clazz);
        } catch (Exception igonred) {
        }
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        try {
            return getApplicationContext().getBean(name, clazz);
        } catch (Exception igonred) {
        }
        return null;
    }

    @Override
    public String getProperty(String key) {
        return getEnvironment().getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return getEnvironment().getProperty(key, defaultValue);
    }

    @Override
    public <T> T getProperty(String key, Class<T> clazz) {
        return getEnvironment().getProperty(key, clazz);
    }

    @Override
    public <T> T getProperty(String key, Class<T> clazz, T defaultValue) {
        return getEnvironment().getProperty(key, clazz, defaultValue);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
