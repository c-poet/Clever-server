package cn.cpoet.yunzhi.note.auth.aspect;

import cn.cpoet.yunzhi.note.api.auth.AuthContext;
import cn.cpoet.yunzhi.note.api.constant.LogicEnum;
import cn.cpoet.yunzhi.note.auth.annotion.HasRole;
import cn.cpoet.yunzhi.note.auth.exception.AuthCheckException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 角色验证
 *
 * @author CPoet
 */
@Aspect
@Component
@RequiredArgsConstructor
public class HasRoleAspect {
    private final AuthContext authContext;

    @Before("@annotation(hasRole)")
    public void before(HasRole hasRole) {
        checkRole(hasRole);
    }

    private void checkRole(HasRole hasRole) {
        if (!authContext.getSubject().hasRole(hasRole.logic(), hasRole.value())) {
            if (LogicEnum.AND.equals(hasRole.logic())) {
                throw new AuthCheckException("权限不足，该访问需要同时具有{}角色.", Arrays.toString(hasRole.value()));
            }
            throw new AuthCheckException("权限不足，该访问需要具有{}中任意角色.", Arrays.toString(hasRole.value()));
        }
    }
}
