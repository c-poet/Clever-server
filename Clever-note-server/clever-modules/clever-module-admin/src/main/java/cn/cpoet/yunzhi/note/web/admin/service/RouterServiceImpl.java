package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.RouterQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.RouterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class RouterServiceImpl implements RouterService {

    @Override
    public PageVO<RouterVO> list(RouterQuery routerQuery) {
        return null;
    }
}
