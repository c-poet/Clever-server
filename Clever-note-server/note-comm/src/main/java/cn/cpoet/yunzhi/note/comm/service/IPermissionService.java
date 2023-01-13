package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.constant.PermissionType;
import cn.cpoet.yunzhi.note.domain.model.Permission;

import java.util.List;

/**
 * @author CPoet
 */
public interface IPermissionService extends BaseService<Permission> {
    /**
     * 获取用户拥有的权限id
     *
     * @param uid 用户uid
     * @return 权限id列表
     */
    List<Long> listIdByUid(Long uid);

    /**
     * 获取用户拥有的权限code
     *
     * @param uid 用户id
     * @return 权限code列表
     */
    List<String> listCodeByUid(Long uid);

    /**
     * 获取用户所有的权限
     *
     * @param uid 用户id
     * @return 权限列表
     */
    List<Permission> listByUid(Long uid);

    /**
     * 获取用户权限
     *
     * @param uid  用户id
     * @param type 权限类型
     * @return 权限列表
     */
    List<Permission> listByUid(Long uid, PermissionType type);
}
