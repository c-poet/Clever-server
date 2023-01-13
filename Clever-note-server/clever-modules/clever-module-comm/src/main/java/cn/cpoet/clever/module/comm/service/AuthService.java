package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.domain.model.Member;
import cn.cpoet.yunzhi.note.web.comm.dto.AccountPassDTO;
import cn.cpoet.yunzhi.note.web.comm.dto.MemberRegisterDTO;
import cn.cpoet.yunzhi.note.web.comm.vo.AuthTokenVO;

/**
 * @author CPoet
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param accountPass 登录信息
     * @return 认证结果
     */
    AuthTokenVO login(AccountPassDTO accountPass);

    /**
     * 判断系统是否开启注册功能
     *
     * @return bool
     */
    boolean isAllowRegister();

    /**
     * 用户注册
     *
     * @param memberRegisterDTO 注册信息
     */
    void register(MemberRegisterDTO memberRegisterDTO);

    /**
     * 签发Token
     *
     * @param member 用户
     * @return Token
     */
    AuthTokenVO signToken(Member member);

    /**
     * 校验用户状态
     *
     * @param member 用户
     */
    void checkMemberStatus(Member member);

    /**
     * 用户注销登录
     *
     * @param subject 主体
     */
    void logout(Subject subject);
}
