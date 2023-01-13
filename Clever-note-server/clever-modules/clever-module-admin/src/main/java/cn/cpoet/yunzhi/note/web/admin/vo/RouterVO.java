package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.yunzhi.note.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.model.Router;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author CPoet
 */
@Data
@Schema(title = "路由信息")
public class RouterVO implements Serializable {

    private static final long serialVersionUID = -4879034549927292723L;

    @Schema(title = "主键")
    private Long id;

    @Schema(title = "路由断言")
    private List<String> predicates;

    @Schema(title = "过滤")
    private List<String> filters;

    @Schema(title = "URL")
    private String uri;

    @Schema(title = "元数据")
    private Map<String, Object> metadata;

    @Schema(title = "路由排序")
    private Integer sorted;

    @Schema(title = "状态")
    private CommStatus status;

    @Schema(title = "说明")
    private String description;

    @Schema(title = "创建时间")
    private LocalDateTime cratedTime;

    @Schema(title = "更新时间")
    private LocalDateTime updatedTime;

    public RouterVO copyOf(Router router) {
        return BeanUtil.copyProperties(router, this);
    }
}
