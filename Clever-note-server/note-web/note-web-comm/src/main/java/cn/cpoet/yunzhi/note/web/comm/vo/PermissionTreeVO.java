package cn.cpoet.yunzhi.note.web.comm.vo;

import cn.cpoet.yunzhi.note.domain.model.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author CPoet
 */
@Data
@Schema(title = "权限树形信息")
public class PermissionTreeVO extends PermissionVO implements Serializable {

    private static final long serialVersionUID = 3275786449152857032L;

    @Schema(title = "子权限列表")
    private List<PermissionTreeVO> children;

    @Override
    public PermissionTreeVO copyOf(Permission permission) {
        super.copyOf(permission);
        children = Collections.emptyList();
        return this;
    }
}
