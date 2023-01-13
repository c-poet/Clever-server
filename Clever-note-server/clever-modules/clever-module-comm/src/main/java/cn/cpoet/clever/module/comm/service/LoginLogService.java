package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.domain.constant.LogoutType;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;

/**
 * @author CPoet
 */
public interface LoginLogService {

    /**
     * 记录登录日志
     *
     * @param loginLog 登录日志
     */
    void loginLog(LoginLog loginLog);

    /**
     * 登出记录
     *
     * @param subject    主体
     * @param token      token
     * @param logoutType 登出类型
     */
    void logoutLog(Subject subject, String token, LogoutType logoutType);
}
