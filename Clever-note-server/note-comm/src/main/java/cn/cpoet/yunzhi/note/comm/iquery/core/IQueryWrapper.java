package cn.cpoet.yunzhi.note.comm.iquery.core;

import cn.cpoet.yunzhi.note.core.query.OrderQuery;
import cn.cpoet.yunzhi.note.core.query.PageQuery;
import io.ebean.typequery.TQRootBean;

/**
 * 查询包装器
 *
 * @author CPoet
 */
public interface IQueryWrapper<T, R extends TQRootBean<T, R>> {

    static <T, R extends TQRootBean<T, R>> IQueryWrapper<T, R> wrapper(R query) {
        return new DefaultQueryWrapper<>(query);
    }

    R done();

    IQueryWrapper<T, R> where(Object bean);

    <B extends OrderQuery> IQueryWrapper<T, R> order(B bean);

    IQueryWrapper<T, R> order(Object bean, OrderQuery orderQuery);

    IQueryWrapper<T, R> page(PageQuery pageQuery);

    IQueryWrapper<T, R> tryWop(Object bean);

    R tryWopDone(Object bean);
}
