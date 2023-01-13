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
@Schema(title = "字典")
@Table(name = "sys_dict")
public class Dict extends BaseModel {

    private static final long serialVersionUID = 6507067387544506538L;


}
