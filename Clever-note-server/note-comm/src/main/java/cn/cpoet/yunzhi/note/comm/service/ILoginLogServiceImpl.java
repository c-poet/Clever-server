package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import cn.cpoet.yunzhi.note.domain.model.query.QLoginLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Slf4j
@Service
public class ILoginLogServiceImpl extends ServiceImpl<LoginLog> implements ILoginLogService {

    @Override
    public LoginLog findByTokenMd5(String tokenMd5) {
        return new QLoginLog()
            .tokenMd5.eq(tokenMd5)
            .findOne();
    }

    @Override
    public void log(LoginLog loginLog) {
        if (loginLog.getLoginTime() == null) {
            loginLog.setLoginTime(AppContextUtil.getReqsTime());
        }
        try {
            insert(loginLog);
        } catch (Exception e) {
            log.warn("登录日志记录失败：{}", e.getMessage(), e);
        }
    }
}
