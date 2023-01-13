package cn.cpoet.yunzhi.note.core.support;

import cn.cpoet.yunzhi.note.api.core.RequestWrapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CPoet
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletRequestWrapper extends AbstractServletRequestWrapper {

    private final static RequestWrapper NONE = new ServletRequestWrapper(null);

    public final HttpServletRequest thisRequest;

    @Override
    protected HttpServletRequest getRequest() {
        return thisRequest;
    }

    public static RequestWrapper wrapper(HttpServletRequest request) {
        if (request == null) {
            return NONE;
        }
        return new ServletRequestWrapper(request);
    }
}
