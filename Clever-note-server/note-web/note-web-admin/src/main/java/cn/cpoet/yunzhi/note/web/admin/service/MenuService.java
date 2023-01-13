package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.web.admin.vo.MenuTreeVO;

import java.util.List;

/**
 * @author CPoet
 */
public interface MenuService {
    /**
     * 获取菜单树
     *
     * @param subject 登录主体
     * @return 菜单树
     */
    List<MenuTreeVO> listMenuTree(Subject subject);
}
