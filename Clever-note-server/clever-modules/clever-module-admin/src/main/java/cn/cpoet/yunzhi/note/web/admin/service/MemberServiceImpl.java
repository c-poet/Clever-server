package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.comm.iquery.core.IQueryWrapper;
import cn.cpoet.yunzhi.note.comm.service.IGroupService;
import cn.cpoet.yunzhi.note.comm.service.IMemberService;
import cn.cpoet.yunzhi.note.comm.service.IRoleService;
import cn.cpoet.yunzhi.note.core.vo.PageVO;
import cn.cpoet.yunzhi.note.domain.model.Group;
import cn.cpoet.yunzhi.note.domain.model.Member;
import cn.cpoet.yunzhi.note.domain.model.Role;
import cn.cpoet.yunzhi.note.domain.model.query.QMember;
import cn.cpoet.yunzhi.note.web.admin.query.MemberQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.MemberVO;
import io.ebean.PagedList;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final IRoleService iRoleService;
    private final IGroupService iGroupService;
    private final IMemberService iMemberService;

    @Override
    public MemberVO getById(Long id) {
        Member member = iMemberService.findById(id);
        return member == null ? null : transform2vo(member);
    }

    @Override
    public PageVO<MemberVO> list(MemberQuery memberQuery) {
        QMember qMember = IQueryWrapper
            .wrapper(new QMember())
            .tryWopDone(memberQuery);
        if (memberQuery.getLocked() != null) {
            if (Boolean.TRUE.equals(memberQuery.getLocked())) {
                qMember.locked.isTrue();
            } else {
                qMember.locked.isFalse();
            }
        }
        PagedList<Member> pagedList = qMember.findPagedList();
        List<MemberVO> list = pagedList.getList().stream().map(this::transform2vo).collect(Collectors.toList());
        return PageVO.of(list, pagedList.getPageIndex(), pagedList.getPageSize(), pagedList.getTotalCount());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(List<Long> id) {
        iMemberService.deleteByIds(id);
    }

    private MemberVO transform2vo(Member member) {
        MemberVO memberVO = new MemberVO().copyOf(member);
        MemberVO.Group innerGroup = new MemberVO.Group();
        innerGroup.setId(member.getGroupId());
        Group group = iGroupService.findByIdOrDefault(member.getGroupId());
        if (group != null) {
            innerGroup.setId(group.getId());
            innerGroup.setName(group.getName());
        }
        memberVO.setGroup(innerGroup);
        List<Role> roles = iRoleService.listByUid(member.getId());
        if (CollectionUtils.isEmpty(roles)) {
            memberVO.setRoles(Collections.emptyList());
        } else {
            List<MemberVO.Role> mrs = new ArrayList<>(roles.size());
            for (Role role : roles) {
                MemberVO.Role innerRole = new MemberVO.Role();
                innerRole.setId(role.getId());
                innerRole.setName(role.getName());
                innerRole.setCode(role.getCode());
            }
            memberVO.setRoles(mrs);
        }
        return memberVO;
    }
}
