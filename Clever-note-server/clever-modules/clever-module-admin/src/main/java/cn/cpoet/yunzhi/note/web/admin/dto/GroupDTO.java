package cn.cpoet.yunzhi.note.web.admin.dto;

import cn.cpoet.yunzhi.note.annotation.validator.group.Insert;
import cn.cpoet.yunzhi.note.annotation.validator.group.Update;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户组")
public class GroupDTO implements Serializable {

    private static final long serialVersionUID = -636275252885221821L;

    @Schema(title = "用户组id")
    @NotNull(message = "用户组id不能为空", groups = Update.class)
    private Long id;

    @Schema(title = "父级id")
    @NotNull(message = "未指定父级用户组", groups = {Insert.class, Update.class})
    private Long parentId;

    @Schema(title = "组名")
    @NotEmpty(message = "组名不能为空", groups = {Insert.class, Update.class})
    private String name;

    @Schema(title = "简称")
    private String shortName;

    @Schema(title = "用户组介绍")
    private String description;

    @Schema(title = "状态")
    @NotNull(message = "未明确组状态", groups = {Insert.class, Update.class})
    private CommStatus status;
}
