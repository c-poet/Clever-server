package cn.cpoet.yunzhi.note.auth.aspect;

import cn.cpoet.yunzhi.note.annotation.auth.AuthContext;
import cn.cpoet.yunzhi.note.annotation.constant.LogicEnum;
import cn.cpoet.yunzhi.note.auth.annotion.HasPermission;
import cn.cpoet.yunzhi.note.auth.exception.AuthCheckException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 权限验证
 *
 * @author CPoet
 */
@Aspect
@Component
@RequiredArgsConstructor
public class HasPermissionAspect {
    private final AuthContext authContext;

    @Before("@annotation(hasPermission)")
    public void before(HasPermission hasPermission) {
        checkPermission(hasPermission);
    }

    private void checkPermission(HasPermission hasPermission) {
        if (!authContext.getSubject().hasPermission(hasPermission.logic(), hasPermission.value())) {
            if (LogicEnum.AND.equals(hasPermission.logic())) {
                throw new AuthCheckException("权限不足，该访问需要同时具有{}权限.", Arrays.toString(hasPermission.value()));
            }
            throw new AuthCheckException("权限不足，该访问需要具有{}中任意权限.", Arrays.toString(hasPermission.value()));
        }
    }
}
