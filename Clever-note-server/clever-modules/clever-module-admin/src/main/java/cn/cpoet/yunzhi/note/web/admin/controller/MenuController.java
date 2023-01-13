package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.yunzhi.note.annotation.auth.Subject;
import cn.cpoet.yunzhi.note.web.admin.service.MenuService;
import cn.cpoet.yunzhi.note.web.admin.vo.MenuTreeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CPoet
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Menu", description = "用户菜单")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "获取用户菜单")
    @PostMapping("/getMenuTree")
    public List<MenuTreeVO> getMenuTree(Subject subject) {
        return menuService.listMenuTree(subject);
    }
}
