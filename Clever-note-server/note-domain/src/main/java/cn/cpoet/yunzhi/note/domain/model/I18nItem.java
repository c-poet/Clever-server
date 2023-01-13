package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import cn.cpoet.yunzhi.note.domain.constant.I18nLocale;
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
@Schema(title = "国际化项")
@Table(name = "sys_i18n_item")
public class I18nItem extends BaseModel {

    private static final long serialVersionUID = 2570654355683405854L;

    @Schema(title = "关联i18n的id")
    @Column(name = "i18n_id")
    private Long i18nId;

    @Schema(title = "内容")
    @Column(name = "content", length = DbLenConst.L512)
    private String content;

    @Schema(title = "语言")
    @Column(name = "locale", nullable = false)
    private I18nLocale locale;
}
