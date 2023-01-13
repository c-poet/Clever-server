package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
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
@Schema(title = "笔记分类（目录)")
@Table(name = "sys_catalog")
public class Catalog extends BaseModel {

    private static final long serialVersionUID = 229375019055657641L;

    @Schema(title = "父级")
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Schema(title = "名称")
    @Column(name = "name", length = DbLenConst.L128, nullable = false)
    private String name;

    @Schema(title = "用户id")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Schema(title = "图标")
    @Column(name = "icon", length = DbLenConst.URL)
    private String icon;

    @Schema(title = "介绍")
    @Column(name = "description", length = DbLenConst.L800)
    private String description;

    @Column(name = "is_item", nullable = false)
    @Schema(title = "是否存在Item项，非目录")
    private Boolean isItem;
}
