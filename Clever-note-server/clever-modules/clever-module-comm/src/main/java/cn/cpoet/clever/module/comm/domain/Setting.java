package cn.cpoet.clever.module.comm.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
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
@Schema(title = "系统配置")
@Table(name = "sys_setting")
public class Setting extends BaseModel {

    private static final long serialVersionUID = 94017610017623397L;

    @Schema(title = "配置名")
    @Column(name = "name", length = DbLenConst.L128, nullable = false)
    private String name;

    @Schema(title = "配置内容")
    @Column(name = "content", length = DbLenConst.L1024)
    private String content;

    @Schema(title = "说明")
    @Column(name = "description", length = DbLenConst.L800)
    private String description;

    @Schema(title = "所属用户id")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Schema(title = "状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;

    @Schema(title = "是否锁定")
    @Column(name = "locked", nullable = false)
    private Boolean locked;

    @Schema(title = "是否内置")
    @Column(name = "is_built_in", nullable = false)
    private Boolean isBuiltIn;
}
