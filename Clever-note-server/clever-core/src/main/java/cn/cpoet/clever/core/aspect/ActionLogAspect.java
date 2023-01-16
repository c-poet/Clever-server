package cn.cpoet.clever.core.aspect;

import cn.cpoet.clever.annotation.ActionLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author CPoet
 */
@Slf4j
@Aspect
public class ActionLogAspect {

    private List<ActionLogConsumer> logConsumers;

    @Around("@annotation(actionLog)")
    public Object around(ProceedingJoinPoint pjp, ActionLog actionLog) throws Throwable {
        Object result = null;
        Throwable throwable = null;
        try {
            result = pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            throwable = e;
        }
        try {
            List<ActionLogConsumer> logConsumers = getLogConsumers();
            if (!CollectionUtils.isEmpty(logConsumers)) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                for (ActionLogConsumer logConsumer : logConsumers) {
                    logConsumer.accept(signature.getParameterNames(), pjp.getArgs(), result, throwable, actionLog);
                }
            }
        } catch (Exception e) {
            log.warn("调用ActionLog消费者异常: {}", e.getMessage(), e);
        }
        if (throwable != null) {
            throw throwable;
        }
        return result;
    }

    private List<ActionLogConsumer> getLogConsumers() {
        if (logConsumers == null) {
            ApplicationContext applicationContext = AppContextUtil.appContext().getApplicationContext();
            Map<String, ActionLogConsumer> consumerMap = applicationContext.getBeansOfType(ActionLogConsumer.class);
            if (!CollectionUtils.isEmpty(consumerMap)) {
                logConsumers = new ArrayList<>(consumerMap.values());
            }
        }
        return logConsumers == null ? Collections.emptyList() : logConsumers;
    }
}
