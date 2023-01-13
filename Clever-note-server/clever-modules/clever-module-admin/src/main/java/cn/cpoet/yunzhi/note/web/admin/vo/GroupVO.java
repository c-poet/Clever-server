package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.clever.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.model.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户分组")
public class GroupVO implements Serializable {

    private static final long serialVersionUID = 5014578487021140280L;

    @Schema(title = "组id")
    private Long id;

    @Schema(title = "父级id")
    private Long parentId;

    @Schema(title = "组名")
    private String name;

    @Schema(title = "简称")
    private String shortName;

    @Schema(title = "组介绍")
    private String description;

    @Schema(title = "状态")
    private CommStatus status;

    @Schema(title = "创建时间")
    private LocalDateTime createdTime;

    @Schema(title = "更新时间")
    private LocalDateTime updatedTime;

    public GroupVO copyOf(Group group) {
        return BeanUtil.copyProperties(group, this);
    }
}
