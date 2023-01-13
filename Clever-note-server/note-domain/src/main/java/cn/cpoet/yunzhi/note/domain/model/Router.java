package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.constant.CompatibleDbTypes;
import io.ebean.annotation.DbJsonB;
import io.ebean.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * @author CPoet
 */
@Data
@Entity
@Table(name = "sys_router")
@Schema(title = "网关路由管理")
public class Router extends BaseModel {

    private static final long serialVersionUID = 8285788496708484357L;

    @DbJsonB
    @Column(name = "predicates")
    @Schema(title = "路由断言")
    private List<String> predicates;

    @DbJsonB
    @Column(name = "filters")
    @Schema(title = "过滤")
    private List<String> filters;

    @Column(name = "uri")
    @Schema(title = "URL")
    private String uri;

    @DbJsonB
    @Column(name = "metadata")
    @Schema(title = "元数据")
    private Map<String, Object> metadata;

    @Index
    @Column(name = "sorted", nullable = false)
    @Schema(title = "路由排序")
    private Integer sorted;

    @Column(name = "status", nullable = false)
    @Schema(title = "状态")
    private CommStatus status;

    @Schema(title = "说明")
    @Column(name = "description", columnDefinition = CompatibleDbTypes.TEXT)
    private String description;
}
