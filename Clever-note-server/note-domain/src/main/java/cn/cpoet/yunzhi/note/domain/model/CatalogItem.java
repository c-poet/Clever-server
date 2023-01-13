package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CatalogItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "目录项")
@Table(name = "sys_catalog_item")
public class CatalogItem extends BaseModel {

    private static final long serialVersionUID = -149568912440480719L;

    @Schema(title = "目录id")
    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;

    @Schema(title = "目录项id")
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Schema(title = "类型")
    @Column(name = "item_type", nullable = false)
    private CatalogItemType itemType;

    @Schema(title = "排序")
    @Column(name = "sorted", nullable = false)
    private Integer sorted;
}
