package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.api.util.AppContextUtil;
import cn.cpoet.yunzhi.note.api.util.DigestUtil;
import cn.cpoet.yunzhi.note.domain.constant.LogoutType;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import cn.cpoet.yunzhi.note.comm.service.ILoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {
    private final ILoginLogService iLoginLogService;

    @Override
    public void loginLog(LoginLog loginLog) {
        iLoginLogService.log(loginLog);
    }

    @Override
    public void logoutLog(Subject subject, String token, LogoutType logoutType) {
        LoginLog loginLog = iLoginLogService.findByTokenMd5(DigestUtil.md5hex(token));
        if (loginLog != null) {
            loginLog.setLogoutType(logoutType);
            loginLog.setLogoutTime(AppContextUtil.getReqsTime());
            iLoginLogService.update(loginLog);
        }
    }
}
