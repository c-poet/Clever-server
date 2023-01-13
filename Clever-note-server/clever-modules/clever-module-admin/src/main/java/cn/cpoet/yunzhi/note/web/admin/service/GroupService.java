package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.dto.GroupDTO;
import cn.cpoet.yunzhi.note.web.admin.query.GroupQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.GroupVO;

import java.util.List;

/**
 * @author CPoet
 */
public interface GroupService {
    /**
     * 根据id查询用户分组信息
     *
     * @param id 分组id
     * @return 用户组
     */
    GroupVO getById(Long id);

    /**
     * 分页查询用户组列表
     *
     * @param groupQuery 查询参数
     * @return 用户组
     */
    PageVO<GroupVO> list(GroupQuery groupQuery);

    /**
     * 新增分组
     *
     * @param groupDTO 用户组信息
     */
    void insert(GroupDTO groupDTO);

    /**
     * 更新分组
     *
     * @param groupDTO 用户组信息
     */
    void update(GroupDTO groupDTO);

    /**
     * 删除用户分组
     *
     * @param id 分组id
     */
    void deleteById(Long id);

    /**
     * 批量删除用户组
     *
     * @param ids id集合
     */
    void deleteById(List<Long> ids);
}
