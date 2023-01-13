package cn.cpoet.yunzhi.note.comm.iquery.core;

import cn.cpoet.yunzhi.note.annotation.constant.LogicEnum;
import cn.cpoet.yunzhi.note.core.query.OrderQuery;
import cn.cpoet.yunzhi.note.core.query.PageQuery;
import io.ebean.typequery.TQRootBean;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询包装默认实现
 *
 * @author CPoet
 */
@Slf4j
public class DefaultQueryWrapper<T, R extends TQRootBean<T, R>> implements IQueryWrapper<T, R> {

    private final R query;

    private LogicEnum lastLogic;

    private final QueryResolveManager resolveManager = QueryResolveManager.INSTANCE;

    public DefaultQueryWrapper(R query) {
        this.query = query;
    }

    @Override
    public R done() {
        return query;
    }

    @Override
    public IQueryWrapper<T, R> where(Object bean) {
        QueryBean queryBean = resolveManager.parseBean(bean);
        return this;
    }


    public IQueryWrapper<T, R> and(Object bean) {
        query.and();
        lastLogic = LogicEnum.AND;
        return where(bean);
    }


    public IQueryWrapper<T, R> or(Object bean) {
        query.or();
        lastLogic = LogicEnum.OR;
        return where(bean);
    }

    public IQueryWrapper<T, R> end() {
        if (lastLogic == null || LogicEnum.AND.equals(lastLogic)) {
            query.endAnd();
        } else {
            query.endOr();
        }
        lastLogic = null;
        return this;
    }

    @Override
    public <B extends OrderQuery> IQueryWrapper<T, R> order(B bean) {
        return order(bean, bean);
    }

    @Override
    public IQueryWrapper<T, R> order(Object bean, OrderQuery orderQuery) {
        return this;
    }

    @Override
    public IQueryWrapper<T, R> page(PageQuery pageQuery) {
        query
            .setFirstRow(pageQuery.getFirstRow())
            .setMaxRows(pageQuery.getMaxRows());
        return this;
    }

    @Override
    public IQueryWrapper<T, R> tryWop(Object bean) {
        where(bean);
        if (bean instanceof OrderQuery) {
            order(bean, (OrderQuery) bean);
        }
        if (bean instanceof PageQuery) {
            page((PageQuery) bean);
        }
        return this;
    }

    @Override
    public R tryWopDone(Object bean) {
        return tryWop(bean).done();
    }
}
