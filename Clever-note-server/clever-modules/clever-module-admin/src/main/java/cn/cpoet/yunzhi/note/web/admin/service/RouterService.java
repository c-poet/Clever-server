package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.RouterQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.RouterVO;

/**
 * @author CPoet
 */
public interface RouterService {
    /**
     * 查询路由列表
     *
     * @param routerQuery 查询参数
     * @return 路由列表
     */
    PageVO<RouterVO> list(RouterQuery routerQuery);
}
