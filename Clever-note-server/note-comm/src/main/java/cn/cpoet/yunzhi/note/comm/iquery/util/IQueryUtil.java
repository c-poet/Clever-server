package cn.cpoet.yunzhi.note.comm.iquery.util;

import cn.cpoet.yunzhi.note.comm.iquery.core.IQueryWrapper;
import io.ebean.typequery.TQRootBean;

/**
 * 工具类
 *
 * @author CPoet
 */
public abstract class IQueryUtil {
    private IQueryUtil() {
    }

    public static <T, R extends TQRootBean<T, R>> IQueryWrapper<T, R> wrapper(R query) {
        return IQueryWrapper.wrapper(query);
    }
}
