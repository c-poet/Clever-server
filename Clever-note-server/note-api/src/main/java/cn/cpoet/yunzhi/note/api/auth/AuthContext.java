package cn.cpoet.yunzhi.note.api.auth;

import cn.cpoet.yunzhi.note.api.core.RequestWrapper;

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

    /**
     * 是否是由Feign发起的请求
     *
     * @return bool
     */
    boolean isFeignCalled();

    /**
     * 是否是由Feign发起的请求
     *
     * @param request 请求环境
     * @return bool
     */
    boolean isFeignCalled(RequestWrapper request);
}
