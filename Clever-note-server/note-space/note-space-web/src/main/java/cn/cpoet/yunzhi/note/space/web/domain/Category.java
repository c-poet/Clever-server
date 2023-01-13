package cn.cpoet.yunzhi.note.space.web.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 分类
 *
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "文章分类")
@Table(name = "spc_category")
public class Category extends BaseModel {

    private static final long serialVersionUID = 3365875428560719948L;

}
