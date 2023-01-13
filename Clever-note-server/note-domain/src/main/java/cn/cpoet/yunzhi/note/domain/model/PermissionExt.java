package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
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
@Schema(title = "RBAC - 权限扩展")
@Table(name = "sys_permission_ext")
public class PermissionExt extends BaseModel {

    private static final long serialVersionUID = 849212281318232168L;

    @Schema(title = "权限Id")
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

    @Schema(title = "访问路径")
    @Column(name = "path", columnDefinition = CompatibleDbTypes.TEXT)
    private String path;

    @Schema(title = "资源图标")
    @Column(name = "icon", length = DbLenConst.URL)
    private String icon;

    @Schema(title = "是否隐藏")
    @Column(name = "hide", nullable = false)
    private Boolean hide;
}
