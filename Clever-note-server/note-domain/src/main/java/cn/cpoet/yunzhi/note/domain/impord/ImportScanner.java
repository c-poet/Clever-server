package cn.cpoet.yunzhi.note.domain.impord;

import java.util.List;

/**
 * @author CPoet
 */
public interface ImportScanner {
    /**
     * 扫描实体数据
     *
     * @param path 扫描的路径
     * @return 扫描结果
     */
    List<ImportEntity> scanner(String path);
}
