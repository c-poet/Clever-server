package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.constant.SystemConst;
import cn.cpoet.yunzhi.note.comm.iquery.util.IQueryUtil;
import cn.cpoet.yunzhi.note.comm.service.IGroupService;
import cn.cpoet.yunzhi.note.comm.service.IMemberService;
import cn.cpoet.clever.core.constant.CommReqsStatus;
import cn.cpoet.clever.core.exception.ReqsException;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.domain.model.Group;
import cn.cpoet.yunzhi.note.domain.model.query.QGroup;
import cn.cpoet.yunzhi.note.web.admin.dto.GroupDTO;
import cn.cpoet.yunzhi.note.web.admin.query.GroupQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.GroupVO;
import io.ebean.PagedList;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final IGroupService iGroupService;
    private final IMemberService iMemberService;

    @Override
    public GroupVO getById(Long id) {
        Group group = iGroupService.findById(id);
        return group == null ? null : new GroupVO().copyOf(group);
    }

    @Override
    public PageVO<GroupVO> list(GroupQuery groupQuery) {
        PagedList<Group> pagedList = IQueryUtil.wrapper(new QGroup())
            .tryWopDone(groupQuery)
            .findPagedList();
        List<GroupVO> list = pagedList.getList().stream().map(new GroupVO()::copyOf).collect(Collectors.toList());
        return PageVO.of(list, pagedList.getPageIndex(), pagedList.getPageSize(), pagedList.getTotalCount());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(GroupDTO groupDTO) {
        if (!Objects.equals(groupDTO.getParentId(), SystemConst.DEFAULT_PARENT_ID) &&
            !iGroupService.existsById(groupDTO.getParentId())) {
            throw new ReqsException(CommReqsStatus.DENY_ACTION, "?????????????????????????????????");
        }
        Group group = new Group();
        group.setParentId(groupDTO.getParentId());
        group.setName(groupDTO.getName());
        group.setShortName(groupDTO.getShortName());
        group.setDescription(groupDTO.getDescription());
        group.setStatus(groupDTO.getStatus());
        iGroupService.insert(group);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GroupDTO groupDTO) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Group group = iGroupService.findById(id);
        if (group != null) {
            // ?????????????????????????????????
            if (iGroupService.existsChildren(id)) {
                throw new ReqsException(CommReqsStatus.DENY_ACTION, "?????????[id={}]????????????", id);
            }
            // ?????????????????????????????????
            if (iMemberService.existsByGroupId(id)) {
                throw new ReqsException(CommReqsStatus.DENY_ACTION, "?????????[id={}]????????????", id);
            }
            iGroupService.delete(group);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(List<Long> ids) {
        ids.forEach(this::deleteById);
    }
}
