package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.core.util.TreeUtil;
import cn.cpoet.yunzhi.note.domain.constant.PermissionType;
import cn.cpoet.yunzhi.note.domain.model.Permission;
import cn.cpoet.yunzhi.note.domain.model.PermissionExt;
import cn.cpoet.yunzhi.note.comm.service.IPermissionExtService;
import cn.cpoet.yunzhi.note.comm.service.IPermissionService;
import cn.cpoet.yunzhi.note.web.admin.vo.MenuTreeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final IPermissionService iPermissionService;
    private final IPermissionExtService iPermissionExtService;

    @Override
    public List<MenuTreeVO> listMenuTree(Subject subject) {
        return TreeUtil.tree(iPermissionService.listByUid(subject.getUid(), PermissionType.MENU),
            this::transform2MenuVO,
            MenuTreeVO::getId,
            MenuTreeVO::getParentId,
            MenuTreeVO::setChildren);
    }

    private MenuTreeVO transform2MenuVO(Permission permission) {
        MenuTreeVO menuTreeVO = new MenuTreeVO().copyOf(permission);
        // 填充扩展信息
        if (Boolean.TRUE.equals(permission.getHasExt())) {
            PermissionExt permissionExt = iPermissionExtService.findByPermissionId(permission.getId());
            if (permissionExt == null) {
                log.warn("权限[id={}]的扩展信息被标记为存在，实际不存在", permission.getId());
            } else {
                menuTreeVO.copyOf(permissionExt);
            }
        }
        return menuTreeVO;
    }
}
