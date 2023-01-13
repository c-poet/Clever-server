package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.constant.SettingKeys;
import cn.cpoet.yunzhi.note.domain.exception.DomainException;
import cn.cpoet.yunzhi.note.domain.model.Group;
import cn.cpoet.yunzhi.note.domain.model.query.QGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IGroupServiceImpl extends ServiceImpl<Group> implements IGroupService {

    private final ISettingService iSettingService;

    @Override
    public Group getDefault() {
        Long groupId = iSettingService.getLong(SettingKeys.DEFAULT_GROUP_ID, SystemConst.SYS_ID);
        if (groupId == null) {
            log.warn("未配置默认用户组，使用默认[id={}]作为默认用户组Id", SystemConst.DEFAULT_ENTITY_ID);
            groupId = SystemConst.DEFAULT_ENTITY_ID;
        }
        Group group = findById(groupId);
        if (group == null) {
            throw new DomainException("获取默认用户组失败：用户组[id={}]不存在", groupId);
        }
        return group;
    }

    @Override
    public Group findByIdOrDefault(Long groupId) {
        if (groupId == null || groupId == SystemConst.DEFAULT_ENTITY_ID) {
            return getDefault();
        }
        Group group = findById(groupId);
        if (group == null) {
            log.info("用户组[id={}]不存在", groupId);
            return getDefault();
        }
        return group;
    }

    @Override
    public boolean existsById(Long groupId) {
        return new QGroup()
            .id.eq(groupId)
            .exists();
    }

    @Override
    public boolean existsChildren(Long groupId) {
        return new QGroup()
            .parentId.eq(groupId)
            .exists();
    }
}
