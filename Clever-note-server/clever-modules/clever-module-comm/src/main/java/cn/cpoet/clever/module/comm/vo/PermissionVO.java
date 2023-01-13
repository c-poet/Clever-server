package cn.cpoet.yunzhi.note.web.comm.vo;

import cn.cpoet.clever.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.constant.PermissionType;
import cn.cpoet.yunzhi.note.domain.model.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "权限")
public class PermissionVO implements Serializable {

    private static final long serialVersionUID = -6528766518296710285L;

    @Schema(title = "权限id")
    private Long id;

    @Schema(title = "父权限id")
    private Long parentId;

    @Schema(title = "资源编码")
    private String code;

    @Schema(title = "资源名称")
    private String name;

    @Schema(title = "绑定的i18n")
    private String bindI18n;

    @Schema(title = "排序")
    private Integer sorted;

    @Schema(title = "权限类型")
    private PermissionType type;

    public PermissionVO copyOf(Permission permission) {
        return BeanUtil.copyProperties(permission, this);
    }
}
