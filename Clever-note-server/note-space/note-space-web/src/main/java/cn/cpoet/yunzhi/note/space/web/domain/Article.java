package cn.cpoet.yunzhi.note.space.web.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文章
 *
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "标题")
@Table(name = "spc_article")
public class Article extends BaseModel {

    private static final long serialVersionUID = -372445176073355247L;

}
