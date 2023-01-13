package cn.cpoet.clever.module.comm.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.constant.CompatibleDbTypes;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import cn.cpoet.yunzhi.note.domain.constant.PermissionType;
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
@Schema(title = "RBAC - 权限")
@Table(name = "sys_permission")
public class Permission extends BaseModel {

    private static final long serialVersionUID = -4709283313209727910L;

    @Schema(title = "父级ID")
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Schema(title = "编码，用于资源权限判断")
    @Column(name = "code", length = DbLenConst.L512, unique = true, nullable = false)
    private String code;

    @Schema(title = "资源名称")
    @Column(name = "name", length = DbLenConst.L512, nullable = false)
    private String name;

    @Schema(title = "资源说明介绍")
    @Column(name = "description", columnDefinition = CompatibleDbTypes.TEXT)
    private String description;

    @Schema(title = "绑定的i18n key")
    @Column(name = "bind_i18n", length = DbLenConst.L128)
    private String bindI18n;

    @Schema(title = "是否内置")
    @Column(name = "is_built_in", nullable = false)
    private Boolean isBuiltIn;

    @Schema(title = "资源状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;

    @Schema(title = "排序")
    @Column(name = "sorted", nullable = false)
    private Integer sorted;

    @Schema(title = "资源类型")
    @Column(name = "type", nullable = false)
    private PermissionType type;

    @Schema(title = "是否存在扩展")
    @Column(name = "has_ext", nullable = false)
    private Boolean hasExt;
}
