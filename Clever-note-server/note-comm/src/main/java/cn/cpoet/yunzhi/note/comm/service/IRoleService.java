package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.model.Role;

import java.util.List;

/**
 * @author CPoet
 */
public interface IRoleService extends BaseService<Role> {
    /**
     * 获取用户拥有的角色id
     *
     * @param uid 用户id
     * @return 角色id列表
     */
    List<Long> listIdByUid(Long uid);

    /**
     * 获取用户拥有的角色code
     *
     * @param uid 用户id
     * @return 角色code列表
     */
    List<String> listCodeByUid(Long uid);

    /**
     * 获取用户拥有的角色列表
     *
     * @param uid 用户id
     * @return 角色列表
     */
    List<Role> listByUid(Long uid);
}
