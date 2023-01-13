package cn.cpoet.yunzhi.note.core.vo;

import cn.cpoet.yunzhi.note.annotation.constant.InfoLevel;
import cn.cpoet.yunzhi.note.annotation.constant.Status;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.core.constant.CommReqsStatus;
import cn.cpoet.yunzhi.note.core.support.GashMap;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Schema(title = "统一响应体")
@SchemaProperties({
    @SchemaProperty(name = ResultVO._CODE_KEY, schema = @Schema(title = "响应码", implementation = Integer.class)),
    @SchemaProperty(name = ResultVO._MESSAGE_KEY, schema = @Schema(title = "提示信息", implementation = String.class)),
    @SchemaProperty(name = ResultVO._DATA_KEY, schema = @Schema(title = "结果数据", implementation = Object.class)),
    @SchemaProperty(name = ResultVO._TIMESTAMP_KEY, schema = @Schema(title = "响应时间戳", implementation = Long.class)),
    @SchemaProperty(name = ResultVO._TRACE_ID, schema = @Schema(title = "链路跟踪Id", implementation = String.class)),
    @SchemaProperty(name = ResultVO._SPAN_ID, schema = @Schema(title = "链路跨度Id", implementation = Integer.class)),
    @SchemaProperty(name = ResultVO._LEVEL, schema = @Schema(title = "信息等级", implementation = String.class)),
    @SchemaProperty(name = ResultVO._EXCEPTION, schema = @Schema(title = "异常信息", description = "问题排查的简单依据", implementation = String.class))
})
@SuppressWarnings("all")
public class ResultVO<T> extends GashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -2888247893297733056L;

    public final static ResultVO<Object> EMPTY_OK = of(CommReqsStatus.SUCCESS);

    public final static String _CODE_KEY = "code";
    public final static String _MESSAGE_KEY = "msg";
    public final static String _TIMESTAMP_KEY = "timestamp";
    public final static String _DATA_KEY = "data";
    public final static String _TRACE_ID = "traceId";
    public final static String _SPAN_ID = "spanId";
    public final static String _LEVEL = "level";
    public final static String _EXCEPTION = "$$exception$$";

    /**
     * 设置响应码
     *
     * @param status 响应码
     */
    public void setCode(Integer status) {
        put(_CODE_KEY, status);
    }

    /**
     * 设置响应信息
     *
     * @param message 响应信息
     */
    public void setMessage(String message) {
        put(_MESSAGE_KEY, message);
    }

    /**
     * 设置响应时间戳
     *
     * @param timestamp 时间戳
     */
    public void setTimestamp(Long timestamp) {
        put(_TIMESTAMP_KEY, timestamp);
    }

    /**
     * 设置响应的数据
     *
     * @param data 响应数据
     */
    public void setData(T data) {
        put(_DATA_KEY, data);
    }

    /**
     * 获取实例
     *
     * @param status 响应状态
     * @param <T>    数据类型
     * @return 返回体实例
     */
    public static <T> ResultVO<T> of(Status status) {
        return of(status, null);
    }

    /**
     * 获取实例
     *
     * @param status 响应状态
     * @param data   响应数据
     * @param <T>    数据类型
     * @return 返回体实例
     */
    public static <T> ResultVO<T> of(Status status, T data) {
        return ofImpl(status.code(), status.message(), status.level(), data);
    }

    /**
     * 获取实例
     *
     * @param status  响应状态
     * @param message 自定义响应信息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 返回体实例
     */
    public static <T> ResultVO<T> of(Status status, String message, T data) {
        return ofImpl(status.code(), message, status.level(), data);
    }

    private static <T> ResultVO<T> ofImpl(int status, String message, InfoLevel level, T data) {
        ResultVO<T> returnVo = new ResultVO<>();
        returnVo.setCode(status);
        returnVo.setMessage(message);
        if (data != null) {
            returnVo.setData(data);
        }
        returnVo.setTimestamp(System.currentTimeMillis());
        // 信息等级
        returnVo.put(_LEVEL, level);
        // 填充链路信息
        returnVo.put(_TRACE_ID, AppContextUtil.getTraceId());
        returnVo.put(_SPAN_ID, AppContextUtil.getSpanId());
        return returnVo;
    }
}
