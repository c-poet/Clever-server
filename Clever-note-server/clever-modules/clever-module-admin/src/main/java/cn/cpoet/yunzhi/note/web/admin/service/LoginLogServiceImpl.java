package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.comm.iquery.util.IQueryUtil;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import cn.cpoet.yunzhi.note.domain.model.query.QLoginLog;
import cn.cpoet.yunzhi.note.web.admin.query.LoginLogQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.LoginLogVO;
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
public class LoginLogServiceImpl implements LoginLogService {

    @Override
    public PageVO<LoginLogVO> list(LoginLogQuery loginLogQuery) {
        PagedList<LoginLog> pagedList = IQueryUtil.wrapper(new QLoginLog())
            .tryWopDone(loginLogQuery)
            .findPagedList();
        List<LoginLogVO> list = pagedList.getList().stream().map(new LoginLogVO()::copyOf).collect(Collectors.toList());
        return PageVO.of(list, pagedList.getPageIndex(), pagedList.getPageSize(), pagedList.getTotalCount());
    }
}
