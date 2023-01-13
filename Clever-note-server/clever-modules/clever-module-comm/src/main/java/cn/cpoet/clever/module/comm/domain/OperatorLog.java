package cn.cpoet.clever.module.comm.domain;

import cn.cpoet.clever.annotation.logger.ActionTypes;
import cn.cpoet.yunzhi.note.domain.base.BaseRecordModel;
import cn.cpoet.yunzhi.note.domain.constant.CompatibleDbTypes;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "操作日志")
@Table(name = "sys_operator_log")
public class OperatorLog extends BaseRecordModel {

    private static final long serialVersionUID = 2272032908240764976L;

    @Schema(title = "链路id")
    @Column(name = "trace_id", length = DbLenConst.L32)
    private String traceId;

    @Schema(title = "跨度id")
    @Column(name = "span_id")
    private Integer spanId;

    @Schema(title = "用户id")
    @Column(name = "member_id")
    private Long memberId;

    @Schema(title = "操作类型")
    @Column(name = "action")
    private ActionTypes action;

    @Schema(title = "操作简要标题")
    @Column(name = "title", length = DbLenConst.L128)
    private String title;

    @Schema(title = "操作详情")
    @Column(name = "description", columnDefinition = CompatibleDbTypes.TEXT)
    private String description;

    @Schema(title = "异常信息摘要")
    @Column(name = "throwable", length = DbLenConst.L512)
    private String throwable;
}
