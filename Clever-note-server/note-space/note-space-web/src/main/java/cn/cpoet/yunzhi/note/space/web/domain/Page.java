package cn.cpoet.yunzhi.note.space.web.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 页面
 *
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "自定义页面")
@Table(name = "spc_page")
public class Page extends BaseModel {

    private static final long serialVersionUID = -6301372886711371177L;

}
