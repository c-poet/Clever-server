package cn.cpoet.yunzhi.note.auth.resolver;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.core.support.ServletRequestWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证主体方法参数解析器
 *
 * @author CPoet
 */
public class SubjectArgResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Subject.class == methodParameter.getParameterType();
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        return AppContextUtil.authContext().getSubject(ServletRequestWrapper.wrapper(request));
    }
}
