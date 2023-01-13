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
@Schema(title = "用户组")
@Table(name = "sys_group")
public class Group extends BaseModel {

    private static final long serialVersionUID = -5954405152326459017L;

    @Schema(title = "父级分组id")
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Schema(title = "用户组名称")
    @Column(name = "name", length = DbLenConst.L128, nullable = false)
    private String name;

    @Schema(title = "用户组简称")
    @Column(name = "short_name", length = DbLenConst.L50)
    private String shortName;

    @Schema(title = "组介绍")
    @Column(name = "description", length = DbLenConst.L800)
    private String description;

    @Schema(title = "状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;
}
