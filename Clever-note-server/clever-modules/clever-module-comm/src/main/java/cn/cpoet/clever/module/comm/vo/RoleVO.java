package cn.cpoet.yunzhi.note.web.comm.vo;

import cn.cpoet.yunzhi.note.domain.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "角色信息")
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 2736134352081727411L;

    @Schema(title = "角色id")
    private Long id;

    @Schema(title = "角色编码")
    private String code;

    @Schema(title = "角色名称")
    private String name;

    @Schema(title = "排序")
    private Integer sorted;

    @Schema(title = "绑定的I18n")
    private String bindI18n;

    public RoleVO copyOf(Role role) {
        id = role.getId();
        code = role.getCode();
        name = role.getName();
        sorted = role.getSorted();
        bindI18n = role.getBindI18n();
        return this;
    }
}
