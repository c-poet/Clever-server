package cn.cpoet.yunzhi.note.api.auth;


import cn.cpoet.yunzhi.note.api.constant.LogicEnum;
import cn.cpoet.yunzhi.note.api.constant.SubjectType;

import java.util.Collection;

/**
 * 当前操作主体
 *
 * @author CPoet
 */
public interface Subject {
    /**
     * 获取当前主体uid
     *
     * @return uid
     */
    long getUid();

    /**
     * 获取用户组id
     *
     * @return 用户组id
     */
    long getGroupId();

    /**
     * 获取当前主体账号
     *
     * @return 账号
     */
    String getAccount();

    /**
     * 获取当前主体的类型
     *
     * @return 主体类型
     */
    SubjectType getType();

    /**
     * 获取主体所有角色
     *
     * @return 角色
     */
    Collection<String> getRoles();

    /**
     * 获取主体所有权限
     *
     * @return 权限
     */
    Collection<String> getPermissions();

    /**
     * 判断当前主体是否具有角色
     *
     * @param logic 逻辑
     * @param roles 角色
     * @return bool
     */
    boolean hasRole(LogicEnum logic, String... roles);

    /**
     * 判断当前主体是否具有权限
     *
     * @param logic       逻辑
     * @param permissions 权限
     * @return bool
     */
    boolean hasPermission(LogicEnum logic, String... permissions);
}
