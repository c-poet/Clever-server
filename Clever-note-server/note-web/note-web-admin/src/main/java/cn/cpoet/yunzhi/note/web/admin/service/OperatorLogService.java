package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.OperatorLogQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.OperatorLogVO;

/**
 * @author CPoet
 */
public interface OperatorLogService {
    /**
     * 查询操作日志列表
     *
     * @param operatorLogQuery 查询参数
     * @return 日志列表
     */
    PageVO<OperatorLogVO> list(OperatorLogQuery operatorLogQuery);
}
