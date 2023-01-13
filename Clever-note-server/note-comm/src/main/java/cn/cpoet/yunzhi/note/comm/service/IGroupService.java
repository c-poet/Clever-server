package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.model.Group;

/**
 * @author CPoet
 */
public interface IGroupService extends BaseService<Group> {
    /**
     * 获取系统默认用户组信息
     *
     * @return 默认用户组
     */
    Group getDefault();

    /**
     * 获取用户组不存在则返回默认用户组信息
     *
     * @param groupId 用户组id
     * @return 用户组信息
     */
    Group findByIdOrDefault(Long groupId);

    /**
     * 判断用户组是否存在
     *
     * @param groupId 用户组id
     * @return bool
     */
    boolean existsById(Long groupId);

    /**
     * 是否存在下级
     *
     * @param groupId 组id
     * @return bool
     */
    boolean existsChildren(Long groupId);
}
