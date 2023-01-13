package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
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
@Schema(title = "RBAC - 角色")
@Table(name = "sys_role")
public class Role extends BaseModel {

    private static final long serialVersionUID = 1758792061604302137L;

    @Schema(title = "角色编码")
    @Column(name = "code", length = DbLenConst.L512, unique = true, nullable = false)
    private String code;

    @Schema(title = "角色名称")
    @Column(name = "name", length = DbLenConst.L512, unique = true, nullable = false)
    private String name;

    @Schema(title = "排序")
    @Column(name = "sorted", nullable = false)
    private Integer sorted;

    @Schema(title = "介绍")
    @Column(name = "description", columnDefinition = CompatibleDbTypes.TEXT)
    private String description;

    @Schema(title = "状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;

    @Schema(title = "绑定的i18n key")
    @Column(name = "bind_i18n")
    private String bindI18n;

    @Schema(title = "是否系统内置")
    @Column(name = "is_built_in", nullable = false)
    private Boolean isBuiltIn;
}
