package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.comm.iquery.core.IQueryWrapper;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.domain.model.OperatorLog;
import cn.cpoet.yunzhi.note.domain.model.query.QOperatorLog;
import cn.cpoet.yunzhi.note.web.admin.query.OperatorLogQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.OperatorLogVO;
import io.ebean.PagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class OperatorLogServiceImpl implements OperatorLogService {
    @Override
    public PageVO<OperatorLogVO> list(OperatorLogQuery operatorLogQuery) {
        PagedList<OperatorLog> pagedList = IQueryWrapper.wrapper(new QOperatorLog())
            .tryWopDone(operatorLogQuery)
            .findPagedList();
        List<OperatorLogVO> list = pagedList.getList().stream().map(new OperatorLogVO()::copyOf).collect(Collectors.toList());
        return PageVO.of(list, pagedList.getPageIndex(), pagedList.getPageSize(), pagedList.getTotalCount());
    }
}
