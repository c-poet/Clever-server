package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseRecordModel;
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
@Schema(title = "角色-权限")
@Table(name = "sys_role_permission")
public class RolePermission extends BaseRecordModel {

    private static final long serialVersionUID = 2583138395500217285L;

    @Schema(title = "角色id")
    @Column(name = "role_id")
    private Long roleId;

    @Schema(title = "权限id")
    @Column(name = "permission_id")
    private Long permissionId;
}
