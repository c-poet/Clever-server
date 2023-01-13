package cn.cpoet.clever.annotation;

/**
 * 操作日志消费
 *
 * @author CPoet
 */
public interface ActionLogConsumer {
    /**
     * 保存日志
     *
     * @param argsNames 调用参数名称
     * @param args      调用参数
     * @param result    调用结果
     * @param throwable 异常信息
     * @param actionLog 操作日志
     */
    void accept(String[] argsNames, Object[] args, Object result, Throwable throwable, ActionLog actionLog);
}
