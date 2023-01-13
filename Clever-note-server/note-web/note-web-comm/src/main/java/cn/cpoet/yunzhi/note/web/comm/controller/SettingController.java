package cn.cpoet.yunzhi.note.web.comm.controller;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.web.comm.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author CPoet
 */
@Tag(name = "Setting", description = "系统配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/setting")
public class SettingController {

    private final SettingService settingService;

    @PostMapping("/getPubSetting")
    @Operation(summary = "获取公共系统配置")
    public Map<String, Object> getPubSetting(@RequestBody(required = false) @Schema(title = "指定配置项") List<String> name) {
        return settingService.getPubSetting(name);
    }

    @PostMapping("/getSetting")
    @Operation(summary = "获取个人配置")
    public Map<String, Object> getSetting(@RequestBody(required = false) @Schema(title = "指定配置项") List<String> name,
                                          Subject subject) {
        return settingService.getSetting(subject, name);
    }
}
