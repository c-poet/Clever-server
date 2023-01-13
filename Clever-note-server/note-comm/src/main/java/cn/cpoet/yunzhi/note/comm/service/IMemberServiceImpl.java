package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.model.Member;
import cn.cpoet.yunzhi.note.domain.model.query.QMember;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Service
public class IMemberServiceImpl extends ServiceImpl<Member> implements IMemberService {
    @Override
    public Member getByAccount(String account) {
        return new QMember()
            .account.eq(account)
            .findOne();
    }

    @Override
    public boolean existsByGroupId(Long groupId) {
        return new QMember()
            .groupId.eq(groupId)
            .exists();
    }
}
