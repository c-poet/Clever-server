package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.clever.constant.ActionTypes;
import cn.cpoet.clever.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.model.OperatorLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "操作日志")
public class OperatorLogVO implements Serializable {

    private static final long serialVersionUID = -3222043074190956039L;

    @Schema(title = "日志id")
    private Long id;

    @Schema(title = "链路id")
    private String traceId;

    @Schema(title = "跨度id")
    private Integer spanId;

    @Schema(title = "用户id")
    private Long memberId;

    @Schema(title = "操作类型")
    private ActionTypes action;

    @Schema(title = "操作简要标题")
    private String title;

    @Schema(title = "操作详情")
    private String description;

    @Schema(title = "异常信息摘要")
    private String throwable;

    public OperatorLogVO copyOf(OperatorLog operatorLog) {
        return BeanUtil.copyProperties(operatorLog, this);
    }
}
