package cn.cpoet.yunzhi.note.auth.aspect;

import cn.cpoet.yunzhi.note.annotation.auth.AuthContext;
import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.annotation.constant.SubjectType;
import cn.cpoet.yunzhi.note.auth.annotion.Authenticated;
import cn.cpoet.yunzhi.note.auth.exception.AuthCheckException;
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
