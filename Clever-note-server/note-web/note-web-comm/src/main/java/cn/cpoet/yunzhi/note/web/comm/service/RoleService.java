package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.web.comm.vo.RoleVO;

import java.util.List;
import java.util.Set;

/**
 * @author CPoet
 */
public interface RoleService {
    /**
     * 获取用户拥有的角色code
     *
     * @param uid 用户id
     * @return 角色code列表
     */
    Set<String> listCodeByUid(Long uid);

    /**
     * 获取用户拥有的角色列表
     *
     * @param subject 主体信息
     * @return 角色列表
     */
    List<RoleVO> listRole(Subject subject);
}
