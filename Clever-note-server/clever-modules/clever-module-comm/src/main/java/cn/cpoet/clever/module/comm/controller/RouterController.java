package cn.cpoet.yunzhi.note.web.comm.controller;

import cn.cpoet.yunzhi.note.annotation.feign.FeignTarget;
import cn.cpoet.yunzhi.note.domain.model.Router;
import cn.cpoet.yunzhi.note.web.comm.service.RouterService;
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
@RequestMapping("/router")
@RequiredArgsConstructor
@Tag(name = "Router", description = "路由信息")
public class RouterController {

    private final RouterService routerService;

    @FeignTarget
    @PostMapping("/listRouter")
    @Operation(summary = "获取有效路由列表")
    public List<Router> listRouter() {
        return routerService.list();
    }
}
