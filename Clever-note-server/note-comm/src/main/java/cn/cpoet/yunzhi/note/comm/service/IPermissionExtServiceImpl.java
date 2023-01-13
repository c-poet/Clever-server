package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.model.PermissionExt;
import cn.cpoet.yunzhi.note.domain.model.query.QPermissionExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class IPermissionExtServiceImpl extends ServiceImpl<PermissionExt> implements IPermissionExtService {
    @Override
    public PermissionExt findByPermissionId(Long permissionId) {
        return new QPermissionExt()
            .permissionId.eq(permissionId)
            .findOne();
    }
}
