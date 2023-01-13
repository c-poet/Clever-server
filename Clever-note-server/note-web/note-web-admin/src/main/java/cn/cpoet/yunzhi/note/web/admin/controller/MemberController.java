package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.yunzhi.note.api.logger.ActionLog;
import cn.cpoet.yunzhi.note.api.logger.ActionTypes;
import cn.cpoet.yunzhi.note.auth.annotion.HasPermission;
import cn.cpoet.yunzhi.note.core.query.IdListQuery;
import cn.cpoet.yunzhi.note.core.query.IdQuery;
import cn.cpoet.yunzhi.note.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.dto.MemberDTO;
import cn.cpoet.yunzhi.note.web.admin.query.MemberQuery;
import cn.cpoet.yunzhi.note.web.admin.service.MemberService;
import cn.cpoet.yunzhi.note.web.admin.vo.MemberVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CPoet
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "用户管理")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/getMemberById")
    @HasPermission("sys:member:query")
    @Operation(summary = "查询用户信息")
    private MemberVO getMemberById(@RequestBody @Validated IdQuery idQuery) {
        return memberService.getById(idQuery.getId());
    }

    @PostMapping("/listMember")
    @HasPermission("sys:member:query")
    @Operation(summary = "查询用户列表")
    private PageVO<MemberVO> listMember(@RequestBody @Validated MemberQuery memberQuery) {
        return memberService.list(memberQuery);
    }

    @PostMapping("/insertMember")
    @HasPermission("sys:member:insert")
    @Operation(summary = "添加用户")
    @ActionLog(title = "添加用户", action = ActionTypes.INSERT)
    public void insertMember(@RequestBody @Validated MemberDTO member) {

    }

    @PostMapping("/updateMember")
    @HasPermission("sys:member:update")
    @Operation(summary = "更新用户")
    @ActionLog(title = "更新用户", action = ActionTypes.UPDATE)
    public void updateMember(@RequestBody @Validated MemberDTO member) {
    }

    @PostMapping("/deleteMember")
    @HasPermission("sys:member:delete")
    @Operation(summary = "删除用户")
    @ActionLog(title = "删除用户", description = "#idQuery.id", action = ActionTypes.DELETE)
    public void deleteMember(@RequestBody @Validated IdListQuery idListQuery) {
        memberService.deleteById(idListQuery.getId());
    }
}
