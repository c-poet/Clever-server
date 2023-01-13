package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.model.Role;
import cn.cpoet.yunzhi.note.domain.model.query.QMemberRole;
import cn.cpoet.yunzhi.note.domain.model.query.QRole;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author CPoet
 */
@Service
public class IRoleServiceImpl extends ServiceImpl<Role> implements IRoleService {
    @Override
    public List<Long> listIdByUid(Long uid) {
        List<Long> roleIds = new QMemberRole()
            .select(QMemberRole.alias().roleId)
            .memberId.eq(uid)
            .asDto(Long.class)
            .findList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new QRole()
            .id.in(roleIds)
            .status.eq(CommStatus.ENABLED)
            .findIds();
    }

    @Override
    public List<String> listCodeByUid(Long uid) {
        List<Long> roleIds = new QMemberRole()
            .select(QMemberRole.alias().roleId)
            .memberId.eq(uid)
            .asDto(Long.class)
            .findList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new QRole()
            .select(QRole.alias().code)
            .id.in(roleIds)
            .status.eq(CommStatus.ENABLED)
            .asDto(String.class)
            .findList();
    }

    @Override
    public List<Role> listByUid(Long uid) {
        List<Long> roleIds = listIdByUid(uid);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return new QRole()
            .id.in(roleIds)
            .status.eq(CommStatus.ENABLED)
            .sorted.asc()
            .findList();
    }
}
