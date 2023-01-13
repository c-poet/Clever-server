package cn.cpoet.clever.core.support;

import cn.cpoet.clever.annotation.core.RequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CPoet
 */
public abstract class AbstractServletRequestWrapper implements RequestWrapper {
    @Override
    public boolean requesting() {
        return getRequest() != null;
    }

    @Override
    public String getRemoteAddr() {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getRemoteAddr();
    }

    @Override
    public String getHeader(String name) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getHeader(name);
    }

    @Override
    public String getParameter(String name) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getParameter(name);
    }

    @Override
    public Object getAttribute(String name) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object o) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            request.setAttribute(name, o);
        }
    }

    /**
     * 获取当前请求的上下文
     *
     * @return 请求上下文
     */
    protected abstract HttpServletRequest getRequest();
}
