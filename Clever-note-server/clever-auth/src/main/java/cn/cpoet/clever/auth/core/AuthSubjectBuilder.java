package cn.cpoet.clever.auth.core;

import cn.cpoet.clever.annotation.context.Subject;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 会对{@link Subject#getRoles()}和{@link Subject#getPermissions()}进行代理
 *
 * @author CPoet
 */
public class AuthSubjectBuilder {
    public final static String GET_ROLES_FUNC_NAME = "getRoles";

    public final static String GET_PERMISSIONS_FUNC_NAME = "getPermissions";

    private final AuthSubject subject;

    private AclQueryFunction getRolesFunc;
    private AclQueryFunction getPermissionsFunc;

    public AuthSubjectBuilder() {
        subject = new AuthSubject();
    }

    public AuthSubjectBuilder withUid(long uid) {
        subject.setUid(uid);
        return this;
    }

    public AuthSubjectBuilder withGroupId(long groupId) {
        subject.setGroupId(groupId);
        return this;
    }

    public AuthSubjectBuilder withAccount(String account) {
        subject.setAccount(account);
        return this;
    }

    public AuthSubjectBuilder withToken(String token) {
        subject.setToken(token);
        return this;
    }

    public AuthSubjectBuilder withGetRoles(AclQueryFunction getRolesFunc) {
        this.getRolesFunc = getRolesFunc;
        return this;
    }

    public AuthSubjectBuilder withGetPermissions(AclQueryFunction getPermissionsFunc) {
        this.getPermissionsFunc = getPermissionsFunc;
        return this;
    }

    public Subject build() {
        // 未设置权限获取函数，不创建代理对象
        if (getRolesFunc == null && getPermissionsFunc == null) {
            return subject;
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AuthSubject.class);
        enhancer.setUseCache(true);
        // 拦截方法目标方法代理
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (getRolesFunc != null && GET_ROLES_FUNC_NAME.equals(method.getName())) {
                return getRolesFunc.apply(subject.getUid());
            }
            if (getPermissionsFunc != null && GET_PERMISSIONS_FUNC_NAME.equals(method.getName())) {
                return getPermissionsFunc.apply(subject.getUid());
            }
            return methodProxy.invoke(subject, objects);
        });
        return (Subject) enhancer.create();
    }
}
