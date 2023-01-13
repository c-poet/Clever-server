package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.comm.service.IRoleService;
import cn.cpoet.yunzhi.note.web.comm.vo.RoleVO;
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
public class RoleServiceImpl implements RoleService {

    private final IRoleService iRoleService;

    @Override
    public Set<String> listCodeByUid(Long uid) {
        return new HashSet<>(iRoleService.listCodeByUid(uid));
    }

    @Override
    public List<RoleVO> listRole(Subject subject) {
        return iRoleService
            .listByUid(subject.getUid())
            .stream()
            .map(new RoleVO()::copyOf)
            .collect(Collectors.toList());
    }
}
