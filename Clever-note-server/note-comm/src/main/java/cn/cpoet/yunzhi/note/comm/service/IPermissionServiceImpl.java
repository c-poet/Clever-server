package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.constant.PermissionType;
import cn.cpoet.yunzhi.note.domain.model.Permission;
import cn.cpoet.yunzhi.note.domain.model.query.QPermission;
import cn.cpoet.yunzhi.note.domain.model.query.QRolePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class IPermissionServiceImpl extends ServiceImpl<Permission> implements IPermissionService {

    private final IRoleService iRoleService;

    @Override
    public List<Long> listIdByUid(Long uid) {
        List<Long> roleIds = iRoleService.listIdByUid(uid);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new QRolePermission()
            .select(QRolePermission.alias().permissionId)
            .roleId.in(roleIds)
            .asDto(Long.class)
            .findList();
    }

    @Override
    public List<String> listCodeByUid(Long uid) {
        List<Long> permissionIds = listIdByUid(uid);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }
        return new QPermission()
            .select(QPermission.alias().code)
            .id.in(permissionIds)
            .status.eq(CommStatus.ENABLED)
            .asDto(String.class)
            .findList();
    }

    @Override
    public List<Permission> listByUid(Long uid) {
        List<Long> permissionIds = listIdByUid(uid);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }
        return new QPermission()
            .id.in(permissionIds)
            .status.eq(CommStatus.ENABLED)
            .sorted.asc()
            .findList();
    }

    @Override
    public List<Permission> listByUid(Long uid, PermissionType type) {
        List<Long> permissionIds = listIdByUid(uid);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }
        return new QPermission()
            .id.in(permissionIds)
            .type.eq(type)
            .status.eq(CommStatus.ENABLED)
            .sorted.asc()
            .findList();
    }
}
