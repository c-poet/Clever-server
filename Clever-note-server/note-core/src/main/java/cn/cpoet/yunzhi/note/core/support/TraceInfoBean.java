package cn.cpoet.yunzhi.note.core.support;

import cn.cpoet.yunzhi.note.api.core.TraceInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 链路信息实现
 *
 * @author CPoet
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceInfoBean implements TraceInfo {
    /**
     * 跨度id
     */
    private final int spanId;

    /**
     * 跟踪id
     */
    private final String traceId;

    @Override
    public int getSpanId() {
        return spanId;
    }

    @Override
    public String getTraceId() {
        return traceId;
    }

    /**
     * 获取链路信息实例
     *
     * @param spanId  跨度id
     * @param traceId 跟踪id
     * @return 链路信息实例
     */
    public static TraceInfoBean of(int spanId, String traceId) {
        return new TraceInfoBean(spanId, traceId);
    }
}
