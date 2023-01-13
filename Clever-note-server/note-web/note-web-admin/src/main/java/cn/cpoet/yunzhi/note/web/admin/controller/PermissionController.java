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
@RequestMapping("/permission")
@Tag(name = "Permission", description = "权限资源管理")
public class PermissionController {
}
