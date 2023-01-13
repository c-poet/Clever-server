package cn.cpoet.clever.module.comm.domain;

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
@Schema(title = "字典项")
@Table(name = "sys_dict_item")
public class DictItem extends BaseModel {

    private static final long serialVersionUID = 8196286450893818536L;

}
