package cn.cpoet.clever.module.file.domain;

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
@Schema(title = "文件")
@Table(name = "sys_file")
public class File extends BaseModel {

    private static final long serialVersionUID = 7600877626582871429L;

}
