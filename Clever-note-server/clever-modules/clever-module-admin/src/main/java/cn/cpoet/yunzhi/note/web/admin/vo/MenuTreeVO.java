package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.yunzhi.note.domain.model.Permission;
import cn.cpoet.yunzhi.note.domain.model.PermissionExt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author CPoet
 */
@Data
@Schema(title = "菜单树形")
public class MenuTreeVO extends MenuVO implements Serializable {

    private static final long serialVersionUID = -980839339258565658L;

    @Schema(title = "子菜单")
    private List<MenuTreeVO> children;

    @Override
    public MenuTreeVO copyOf(Permission permission) {
        super.copyOf(permission);
        children = Collections.emptyList();
        return this;
    }

    @Override
    public MenuTreeVO copyOf(PermissionExt permissionExt) {
        super.copyOf(permissionExt);
        return this;
    }
}
