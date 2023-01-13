package cn.cpoet.yunzhi.note.domain.configuration.auto;

import lombok.Data;

import java.util.List;

/**
 * 数据导入导出配置
 *
 * @author CPoet
 */
@Data
public class ImportProperties {
    /**
     * 扫描的根路径
     */
    private List<String> paths;
}
