package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.clever.auth.annotion.HasPermission;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.RouterQuery;
import cn.cpoet.yunzhi.note.web.admin.service.RouterService;
import cn.cpoet.yunzhi.note.web.admin.vo.RouterVO;
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
@RequestMapping("/router")
@Tag(name = "Router", description = "路由管理")
public class RouterController {

    private final RouterService routerService;

    @PostMapping("/listRouter")
    @Operation(summary = "查询路由列表")
    @HasPermission("sys:router:query")
    public PageVO<RouterVO> listRouter(@RequestBody RouterQuery routerQuery) {
        return routerService.list(routerQuery);
    }

    @Operation(summary = "添加路由")
    @HasPermission("sys:router:insert")
    @PostMapping("/insertRouter")
    public void insertRouter() {
    }

    @Operation(summary = "更新路由")
    @HasPermission("sys:router:update")
    @PostMapping("/updateRouter")
    public void updateRouter() {
    }

    @Operation(summary = "删除路由")
    @HasPermission("sys:router:delete")
    @PostMapping("/deleteRouter")
    public void deleteRouter() {
    }
}
