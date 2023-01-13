package cn.cpoet.clever.auth.aspect;

import cn.cpoet.clever.annotation.context.AuthContext;
import cn.cpoet.clever.auth.annotion.HasPermission;
import cn.cpoet.clever.auth.exception.AuthCheckException;
import cn.cpoet.clever.constant.LogicEnum;
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
                throw new AuthCheckException("权限不足，该访问需要同时具有" + Arrays.toString(hasPermission.value()) + "权限.");
            }
            throw new AuthCheckException("权限不足，该访问需要具有" + Arrays.toString(hasPermission.value()) + "中任意权限.");
        }
    }
}
