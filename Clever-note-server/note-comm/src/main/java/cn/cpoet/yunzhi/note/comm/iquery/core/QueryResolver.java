package cn.cpoet.yunzhi.note.comm.iquery.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 条件解析器
 *
 * @author CPoet
 */
public interface QueryResolver {

    List<Annotation> supports();

    void handle(ResolveContext context);
}
