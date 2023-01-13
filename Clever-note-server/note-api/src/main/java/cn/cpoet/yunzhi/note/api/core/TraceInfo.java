package cn.cpoet.yunzhi.note.api.core;

/**
 * 链路信息
 *
 * @author CPoet
 */
public interface TraceInfo {
    /**
     * 链路跨度id
     *
     * @return 跨度id
     */
    int getSpanId();

    /**
     * 链路跟踪Id
     *
     * @return 跟踪id
     */
    String getTraceId();
}
