package cn.cpoet.yunzhi.note.core.query;

import cn.cpoet.yunzhi.note.annotation.validator.group.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

/**
 * @author CPoet
 */
@Data
@Schema(title = "分页查询")
public class PageQuery extends OrderQuery {

    private static final long serialVersionUID = -1953200387786192673L;

    @Schema(title = "页码")
    @NotNull(message = "需要指定页码")
    @Size(min = 1, message = "页码不能小于 {min}"
        , groups = {Default.class, Query.class})
    private Integer pageNo;

    @Schema(title = "每页显示的数据量")
    @NotNull(message = "必须指定每页显示的数量")
    @Size(min = 5, max = 150, message = "每页能显示的数据量在{min}至{max}间"
        , groups = {Default.class, Query.class})
    private Integer pageSize;

    /**
     * 查询偏移量
     *
     * @return 偏移量
     */
    public int getFirstRow() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 获取返回最大的条数，limit数量
     *
     * @return limit数量
     */
    public int getMaxRows() {
        return pageSize;
    }
}
