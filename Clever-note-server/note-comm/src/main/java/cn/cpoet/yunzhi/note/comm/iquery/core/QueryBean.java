package cn.cpoet.yunzhi.note.comm.iquery.core;

import lombok.Data;

import java.util.List;

/**
 * query bean info
 *
 * @author CPoet
 */
@Data
public class QueryBean {
    private Class<?> clazz;

    private List<QueryField> queryFields;
}
