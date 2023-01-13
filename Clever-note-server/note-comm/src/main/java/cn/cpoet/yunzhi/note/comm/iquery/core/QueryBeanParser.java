package cn.cpoet.yunzhi.note.comm.iquery.core;

/**
 * 查询Bean解析器
 *
 * @author CPoet
 */
public interface QueryBeanParser {
    /**
     * 解析bean
     *
     * @param bean 查询bean
     * @return 解析结果
     */
    QueryBean parseBean(Object bean);
}
