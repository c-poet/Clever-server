package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.clever.annotation.RequestWrapper;
import cn.cpoet.clever.core.exception.ReqsException;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.annotation.util.DigestUtil;
import cn.cpoet.clever.auth.component.JwtSupport;
import cn.cpoet.clever.auth.core.AuthSubject;
import cn.cpoet.clever.core.constant.CommReqsStatus;
import cn.cpoet.clever.core.util.PassUtil;
import cn.cpoet.clever.core.util.ReqsUtil;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.constant.LoginType;
import cn.cpoet.yunzhi.note.domain.constant.LogoutType;
import cn.cpoet.yunzhi.note.domain.constant.SettingKeys;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import cn.cpoet.yunzhi.note.domain.model.Member;
import cn.cpoet.yunzhi.note.comm.service.IMemberService;
import cn.cpoet.yunzhi.note.comm.service.ISettingService;
import cn.cpoet.yunzhi.note.web.comm.constant.ReqsStatus;
import cn.cpoet.yunzhi.note.web.comm.dto.AccountPassDTO;
import cn.cpoet.yunzhi.note.web.comm.dto.MemberRegisterDTO;
import cn.cpoet.yunzhi.note.web.comm.vo.AuthTokenVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtSupport jwtSupport;
    private final IMemberService iMemberService;
    private final ISettingService iSettingService;
    private final LoginLogService loginLogService;

    @Override
    public AuthTokenVO login(AccountPassDTO accountPass) {
        Member member = iMemberService.getByAccount(accountPass.getAccount());
        if (member == null) {
            throw new ReqsException(ReqsStatus.ACCOUNT_PASS_ERROR);
        }
        if (!PassUtil.verify(accountPass.getPassword(), member.getSalt(), member.getPassword())) {
            throw new ReqsException(ReqsStatus.ACCOUNT_PASS_ERROR);
        }
        checkMemberStatus(member);
        AuthTokenVO authTokenVO = signToken(member);
        handleLoginLog(authTokenVO, member, accountPass);
        return authTokenVO;
    }

    @Override
    public boolean isAllowRegister() {
        return iSettingService.isEnabled(SettingKeys.REGISTER_ENABLED);
    }

    @Override
    public void register(MemberRegisterDTO memberRegisterDTO) {
        if (!isAllowRegister()) {
            throw new ReqsException(CommReqsStatus.ACCESS_DENIED, "?????????????????????");
        }
    }

    @Override
    public AuthTokenVO signToken(Member member) {
        AuthTokenVO authToken = new AuthTokenVO();
        authToken.setUid(member.getId());
        Subject subject = jwtSupport.genAuthSubject(member.getId(), member.getAccount(), member.getGroupId(), null);
        authToken.setToken(jwtSupport.getToken(subject));
        return authToken;
    }

    @Override
    public void checkMemberStatus(Member member) {
        if (CommStatus.DISABLED.equals(member.getStatus())) {
            throw new ReqsException(ReqsStatus.MEMBER_DISABLED);
        }
        if (Boolean.TRUE.equals(member.getLocked())) {
            if (member.getLockedExpired() == null) {
                throw new ReqsException(ReqsStatus.MEMBER_LOCKED);
            }
            LocalDateTime lockedExpired = member.getLockedExpired();
            // ??????????????????
            if (lockedExpired.isBefore(AppContextUtil.getReqsTime())) {
                member.setLocked(Boolean.FALSE);
                iMemberService.update(member);
            } else {
                String expiredTime = AppContextUtil.convert(member.getExpiredTime(), String.class);
                throw new ReqsException(ReqsStatus.MEMBER_LOCKED, "??????????????????{}", expiredTime);
            }
        }
        if (LocalDateTime.now().isAfter(member.getExpiredTime())) {
            throw new ReqsException(ReqsStatus.MEMBER_EXPIRED);
        }
    }

    @Override
    public void logout(Subject subject) {
        if (!(subject instanceof AuthSubject)) {
            throw new ReqsException(CommReqsStatus.ACCESS_DENIED, "??????[uid={}]??????????????????", subject.getUid());
        }
        String token = ((AuthSubject) subject).getToken();
        if (StringUtils.hasText(token)) {
            loginLogService.logoutLog(subject, token, LogoutType.MANUAL);
        }
    }

    /**
     * ??????????????????
     *
     * @param authTokenVO ??????Token??????
     * @param member      ????????????
     * @param accountPass ????????????
     */
    private void handleLoginLog(AuthTokenVO authTokenVO, Member member, AccountPassDTO accountPass) {
        RequestWrapper requestWrapper = AppContextUtil.getRequestWrapper();
        LoginLog loginLog = new LoginLog();
        loginLog.setMemberId(member.getId());
        loginLog.setAccount(accountPass.getAccount());
        loginLog.setToken(authTokenVO.getToken());
        loginLog.setTokenMd5(DigestUtil.md5hex(authTokenVO.getToken()));
        loginLog.setLoginType(LoginType.ACCOUNT);
        loginLog.setIpAddr(ReqsUtil.findIpAddress(requestWrapper));
        loginLog.setUserAgent(ReqsUtil.getUserAgent(requestWrapper));
        loginLog.setOs(accountPass.getOs());
        loginLog.setScreen(accountPass.getScreen());
        loginLog.setLoginTime(AppContextUtil.getReqsTime());
        loginLogService.loginLog(loginLog);
    }
}
