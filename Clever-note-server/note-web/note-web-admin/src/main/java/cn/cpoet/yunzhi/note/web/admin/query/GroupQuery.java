package cn.cpoet.yunzhi.note.web.admin.query;

import cn.cpoet.yunzhi.note.core.query.PageQuery;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户组查询")
public class GroupQuery extends PageQuery {

    private static final long serialVersionUID = -1355119826567444444L;

    @Schema(title = "组id")
    private Long id;

    @Schema(title = "父级id")
    private Long parentId;

    @Schema(title = "组名")
    private String name;

    @Schema(title = "组简称")
    private String shortName;

    @Schema(title = "状态")
    private CommStatus status;
}
