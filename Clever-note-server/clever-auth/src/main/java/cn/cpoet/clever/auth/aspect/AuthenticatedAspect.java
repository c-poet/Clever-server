package cn.cpoet.clever.auth.aspect;

import cn.cpoet.clever.annotation.context.AuthContext;
import cn.cpoet.clever.annotation.context.Subject;
import cn.cpoet.clever.auth.annotion.Authenticated;
import cn.cpoet.clever.auth.exception.AuthCheckException;
import cn.cpoet.clever.constant.SubjectType;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 认证
 *
 * @author CPoet
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticatedAspect {
    private final AuthContext authContext;

    @Before("@annotation(authenticated)")
    public void before(Authenticated authenticated) {
        Subject subject = authContext.getSubject();
        SubjectType[] subjects = authenticated.subjects();
        for (SubjectType subjectType : subjects) {
            if (subjectType.equals(subject.getType())) {
                return;
            }
        }
        throw new AuthCheckException(StringUtils.hasText(authenticated.message())
            ? authenticated.message()
            : "The subject type does not meet the requirements");
    }
}
