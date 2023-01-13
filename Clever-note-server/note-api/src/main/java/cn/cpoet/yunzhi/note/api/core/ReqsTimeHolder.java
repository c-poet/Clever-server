package cn.cpoet.yunzhi.note.api.core;

import org.springframework.core.NamedThreadLocal;

import java.time.LocalDateTime;

/**
 * 记录请求的开始时间
 *
 * @author CPoet
 */
public abstract class ReqsTimeHolder {
    private ReqsTimeHolder() {
    }

    private final static ThreadLocal<LocalDateTime> TIME_THREAD_LOCAL = new NamedThreadLocal<>("RequestStartTimeThreadLocal");

    /**
     * 记录时间
     */
    public static void start() {
        start(LocalDateTime.now());
    }

    /**
     * 记录时间
     *
     * @param startTime 指定时间
     */
    public static void start(LocalDateTime startTime) {
        TIME_THREAD_LOCAL.set(startTime);
    }

    /**
     * 获取时间
     *
     * @return 请求开始时间
     */
    public static LocalDateTime get() {
        return TIME_THREAD_LOCAL.get();
    }

    /**
     * 移出上下文
     */
    public static void remove() {
        TIME_THREAD_LOCAL.remove();
    }
}
