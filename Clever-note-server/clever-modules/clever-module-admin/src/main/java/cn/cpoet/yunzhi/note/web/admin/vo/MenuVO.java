package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.clever.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.model.Permission;
import cn.cpoet.yunzhi.note.domain.model.PermissionExt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "菜单")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 5811128361448730217L;

    @Schema(title = "id")
    private Long id;

    @Schema(title = "父级id")
    private Long parentId;

    @Schema(title = "编码")
    private String code;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "路径")
    private String path;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "是否隐藏")
    private Boolean hide;

    @Schema(title = "绑定的i18n")
    private String bindI18n;

    @Schema(title = "排序")
    private Integer sorted;

    public MenuVO copyOf(Permission permission) {
        return BeanUtil.copyProperties(permission, this);
    }

    public MenuVO copyOf(PermissionExt permissionExt) {
        return BeanUtil.copyProperties(permissionExt, this);
    }
}
