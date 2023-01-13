package cn.cpoet.yunzhi.note.web.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CPoet
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@Tag(name = "Role", description = "角色管理")
public class RoleController {
}
