package cn.cpoet.yunzhi.note.comm.iquery.resolver;

import cn.cpoet.yunzhi.note.comm.iquery.core.QueryResolver;
import cn.cpoet.yunzhi.note.comm.iquery.core.ResolveContext;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

/**
 * 抽象查询解析器
 *
 * @author CPoet
 */
public abstract class AbstractQueryResolver implements QueryResolver {
    @Override
    public List<Annotation> supports() {
        return Collections.singletonList(support());
    }

    protected abstract Annotation support();

    @Override
    public void handle(ResolveContext context) {

    }
}
