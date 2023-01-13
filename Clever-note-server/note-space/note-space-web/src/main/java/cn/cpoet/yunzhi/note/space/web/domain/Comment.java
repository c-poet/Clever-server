package cn.cpoet.yunzhi.note.space.web.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "文章评论")
@Table(name = "spc_comment")
public class Comment extends BaseModel {

    private static final long serialVersionUID = 1817711261427954372L;

}
