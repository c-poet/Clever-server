package cn.cpoet.yunzhi.note.web.gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author CPoet
 */
@Data
@Schema(title = "路由信息")
public class RouterDTO implements Serializable {

    private static final long serialVersionUID = -1388083324532608055L;

    @Schema(title = "路由id")
    private Long id;

    @Schema(title = "路由断言")
    private List<String> predicates;

    @Schema(title = "过滤")
    private List<String> filters;

    @Schema(title = "匹配uri")
    private String uri;

    @Schema(title = "元数据")
    private Map<String, Object> metadata;

    @Schema(title = "路由排序")
    private Integer sorted;
}
