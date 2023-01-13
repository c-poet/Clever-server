package cn.cpoet.yunzhi.note.web.admin.query;

import cn.cpoet.clever.constant.ActionTypes;
import cn.cpoet.clever.core.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "操作日志查询")
public class OperatorLogQuery extends PageQuery {

    private static final long serialVersionUID = 8861345721962687063L;

    @Schema(title = "链路id")
    private String traceId;

    @Schema(title = "用户id")
    private Long memberId;

    @Schema(title = "操作类型")
    private ActionTypes action;

    @Schema(title = "操作简要标题")
    private String title;

    @Schema(title = "操作详情")
    private String description;

    @Schema(title = "开始时间起始")
    private LocalDateTime createdTimeStart;

    @Schema(title = "开始时间结束")
    private LocalDateTime createdTimeEnd;
}
