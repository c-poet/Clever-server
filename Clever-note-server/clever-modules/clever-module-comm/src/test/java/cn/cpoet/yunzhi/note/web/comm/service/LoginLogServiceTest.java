package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.yunzhi.note.domain.constant.LoginType;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class LoginLogServiceTest {
    @Autowired
    private LoginLogService loginLogService;

    @Test
    void log() {
        LoginLog loginLog = new LoginLog();
        loginLog.setMemberId(SystemConst.DEFAULT_ENTITY_ID);
        loginLog.setAccount("test");
        loginLog.setLoginType(LoginType.ACCOUNT);
        loginLog.setIpAddr("");
        loginLog.setUserAgent("");
        loginLog.setOs("");
        loginLog.setScreen("");
        loginLog.setLoginTime(LocalDateTime.now());
        loginLogService.loginLog(loginLog);
    }
}