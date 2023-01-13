package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.core.exception.ReqsException;
import cn.cpoet.yunzhi.note.core.constant.CommReqsStatus;
import cn.cpoet.yunzhi.note.domain.model.Group;
import cn.cpoet.yunzhi.note.domain.model.Member;
import cn.cpoet.yunzhi.note.comm.service.IGroupService;
import cn.cpoet.yunzhi.note.comm.service.IMemberService;
import cn.cpoet.yunzhi.note.web.comm.vo.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = SystemConst.CACHE_NAMES_MEMBER)
public class MemberServiceImpl implements MemberService {

    private final IGroupService iGroupService;
    private final IMemberService iMemberService;

    @Override
    @Cacheable
    public MemberInfoVO getInfo(Subject subject) {
        Member member = iMemberService.findById(subject.getUid());
        if (member == null) {
            throw new ReqsException(CommReqsStatus.USER_STATUS_ERROR);
        }
        MemberInfoVO infoVO = new MemberInfoVO().copyOf(member);
        Group group = iGroupService.findByIdOrDefault(member.getGroupId());
        MemberInfoVO.Group innerGroup = new MemberInfoVO.Group();
        innerGroup.setId(group.getId());
        innerGroup.setName(group.getName());
        innerGroup.setShortName(group.getShortName());
        return infoVO;
    }
}
