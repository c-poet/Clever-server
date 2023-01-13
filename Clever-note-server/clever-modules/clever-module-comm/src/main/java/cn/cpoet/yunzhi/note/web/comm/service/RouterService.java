package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.domain.model.Router;

import java.util.List;

/**
 * @author CPoet
 */
public interface RouterService {
    /**
     * 获取所有有效的路由列表
     *
     * @return 路由列表
     */
    List<Router> list();
}
