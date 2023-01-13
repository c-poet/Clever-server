package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
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
@Schema(title = "国际化I18n")
@Table(name = "sys_i18n")
public class I18n extends BaseModel {

    private static final long serialVersionUID = 1501653975410582012L;

    @Schema(title = "信息唯一编码")
    @Column(name = "name", length = DbLenConst.L128, unique = true)
    private String name;

    @Schema(title = "使用的场景")
    @Column(name = "scenes", length = DbLenConst.L50, nullable = false)
    private String scenes;

    @Schema(title = "使用分组")
    @Column(name = "group_name", length = DbLenConst.L50, nullable = false)
    private String groupName;

    @Schema(title = "状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;

    @Schema(title = "是否内置")
    @Column(name = "is_built_in", nullable = false)
    private Boolean isBuiltIn;
}
