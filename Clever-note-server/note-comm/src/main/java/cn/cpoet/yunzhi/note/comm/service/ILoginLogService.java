package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;

/**
 * @author CPoet
 */
public interface ILoginLogService extends BaseService<LoginLog> {
    /**
     * 查询日志
     *
     * @param tokenMd5 token md5值
     * @return 登录日志
     */
    LoginLog findByTokenMd5(String tokenMd5);

    /**
     * 记录登录日志
     *
     * @param loginLog 登录日志
     */
    void log(LoginLog loginLog);
}
