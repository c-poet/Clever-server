package cn.cpoet.yunzhi.note.domain.impord;

import lombok.Data;

import java.util.List;

/**
 * 导入实体信息
 *
 * @author CPoet
 */
@Data
public class ImportEntity {
    /**
     * 实体类型
     */
    private String type;

    /**
     * 实体值
     */
    private List<Object> entities;
}
