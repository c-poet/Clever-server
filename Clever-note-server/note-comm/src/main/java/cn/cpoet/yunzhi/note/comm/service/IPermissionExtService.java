package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.model.PermissionExt;

/**
 * @author CPoet
 */
public interface IPermissionExtService extends BaseService<PermissionExt> {
    /**
     * 根据权限id查询扩展
     *
     * @param permissionId 权限id
     * @return 权限扩展
     */
    PermissionExt findByPermissionId(Long permissionId);
}
