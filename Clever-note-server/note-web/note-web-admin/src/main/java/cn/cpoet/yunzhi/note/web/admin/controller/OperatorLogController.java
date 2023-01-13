package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.yunzhi.note.auth.annotion.HasPermission;
import cn.cpoet.yunzhi.note.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.OperatorLogQuery;
import cn.cpoet.yunzhi.note.web.admin.service.OperatorLogService;
import cn.cpoet.yunzhi.note.web.admin.vo.OperatorLogVO;
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
@RequestMapping("/operatorLog")
@Tag(name = "OperatorLog", description = "操作日志")
public class OperatorLogController {

    private final OperatorLogService operatorLogService;

    @Operation(summary = "查询操作日志")
    @HasPermission("sys:operatorLog:query")
    @PostMapping("/listOperatorLog")
    public PageVO<OperatorLogVO> listOperatorLog(@RequestBody OperatorLogQuery operatorLogQuery) {
        return operatorLogService.list(operatorLogQuery);
    }

}
