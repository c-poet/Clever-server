package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.yunzhi.note.web.admin.service.SettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CPoet
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/setting")
@Tag(name = "Setting", description = "系统配置")
public class SettingController {
    private final SettingService settingService;
}
