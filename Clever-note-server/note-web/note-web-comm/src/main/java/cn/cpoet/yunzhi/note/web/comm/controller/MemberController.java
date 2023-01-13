package cn.cpoet.yunzhi.note.web.comm.controller;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.api.feign.FeignTarget;
import cn.cpoet.yunzhi.note.core.query.IdQuery;
import cn.cpoet.yunzhi.note.core.feign.MemberFeign;
import cn.cpoet.yunzhi.note.web.comm.service.MemberService;
import cn.cpoet.yunzhi.note.web.comm.service.PermissionService;
import cn.cpoet.yunzhi.note.web.comm.service.RoleService;
import cn.cpoet.yunzhi.note.web.comm.vo.MemberInfoVO;
import cn.cpoet.yunzhi.note.web.comm.vo.PermissionTreeVO;
import cn.cpoet.yunzhi.note.web.comm.vo.PermissionVO;
import cn.cpoet.yunzhi.note.web.comm.vo.RoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author CPoet
 */
@Primary
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "用户信息")
public class MemberController implements MemberFeign {
    private final RoleService roleService;
    private final MemberService memberService;
    private final PermissionService permissionService;

    @PostMapping("/getPersonInfo")
    @Operation(summary = "获取用户基本信息")
    public MemberInfoVO getPersonInfo(Subject subject) {
        return memberService.getInfo(subject);
    }

    @PostMapping("/listMemberRole")
    @Operation(summary = "获取用户拥有的角色列表")
    public List<RoleVO> listMemberRole(Subject subject) {
        return roleService.listRole(subject);
    }

    @PostMapping("/listMemberPermission")
    @Operation(summary = "获取用户拥有的权限列表")
    public List<PermissionVO> listMemberPermission(Subject subject) {
        return permissionService.listPermission(subject);
    }

    @PostMapping("/listMemberPermissionTree")
    @Operation(summary = "获取用户拥有的权限树形")
    public List<PermissionTreeVO> listMemberPermissionTree(Subject subject) {
        return permissionService.listPermissionTree(subject);
    }

    @Override
    @FeignTarget
    @Operation(summary = "获取用户有效角色code")
    public Set<String> listRole(IdQuery idQuery) {
        return roleService.listCodeByUid(idQuery.getId());
    }

    @Override
    @FeignTarget
    @Operation(summary = "获取用户有效权限code")
    public Set<String> listPermission(IdQuery idQuery) {
        return permissionService.listCodeByUid(idQuery.getId());
    }
}
