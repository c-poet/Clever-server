package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.clever.auth.annotion.HasPermission;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.LoginLogQuery;
import cn.cpoet.yunzhi.note.web.admin.service.LoginLogService;
import cn.cpoet.yunzhi.note.web.admin.vo.LoginLogVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CPoet
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginLog")
@Tag(name = "LoginLog", description = "登录日志")
public class LoginLogController {

    private final LoginLogService loginLogService;

    @PostMapping("/listLoginLog")
    @HasPermission("sys:loginLog:query")
    @Operation(summary = "查询日志列表")
    public PageVO<LoginLogVO> listLoginLog(@RequestBody LoginLogQuery loginLogQuery) {
        return loginLogService.list(loginLogQuery);
    }

}
