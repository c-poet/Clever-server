package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.core.util.TreeUtil;
import cn.cpoet.yunzhi.note.comm.service.IPermissionService;
import cn.cpoet.yunzhi.note.web.comm.vo.PermissionTreeVO;
import cn.cpoet.yunzhi.note.web.comm.vo.PermissionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final IPermissionService iPermissionService;

    @Override
    public Set<String> listCodeByUid(Long uid) {
        return new HashSet<>(iPermissionService.listCodeByUid(uid));
    }

    @Override
    public List<PermissionVO> listPermission(Subject subject) {
        return iPermissionService
            .listByUid(subject.getUid())
            .stream()
            .map(new PermissionVO()::copyOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<PermissionTreeVO> listPermissionTree(Subject subject) {
        return TreeUtil.tree(iPermissionService.listByUid(subject.getUid()),
            new PermissionTreeVO()::copyOf,
            PermissionTreeVO::getId,
            PermissionTreeVO::getParentId,
            PermissionTreeVO::setChildren);
    }
}
