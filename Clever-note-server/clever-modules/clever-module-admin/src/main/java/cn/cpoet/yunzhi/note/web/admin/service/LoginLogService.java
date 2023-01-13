package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.LoginLogQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.LoginLogVO;

/**
 * @author CPoet
 */
public interface LoginLogService {
    /**
     * 日志查询
     *
     * @param loginLogQuery 查询条件
     * @return 日志列表
     */
    PageVO<LoginLogVO> list(LoginLogQuery loginLogQuery);
}
