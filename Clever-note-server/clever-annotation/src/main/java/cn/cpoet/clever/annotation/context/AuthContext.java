package cn.cpoet.clever.annotation.context;

import cn.cpoet.clever.annotation.RequestWrapper;

/**
 * 认证上下文
 *
 * @author CPoet
 */
public interface AuthContext {
    /**
     * 获取当前操作主体
     *
     * @return 当前主体
     */
    Subject getSubject();

    /**
     * 获取当前操作主体
     *
     * @param request 请求环境
     * @return 操作主体
     */
    Subject getSubject(RequestWrapper request);

    /**
     * 获取默认请求环境
     *
     * @return 默认请求环境
     */
    RequestWrapper getDefaultRequestWrapper();
}
