package cn.cpoet.yunzhi.note.web.admin.controller;

import cn.cpoet.clever.constant.LogicEnum;
import cn.cpoet.clever.core.validator.group.Insert;
import cn.cpoet.clever.core.validator.group.Update;
import cn.cpoet.clever.auth.annotion.HasPermission;
import cn.cpoet.clever.core.query.IdListQuery;
import cn.cpoet.clever.core.query.IdQuery;
import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.dto.GroupDTO;
import cn.cpoet.yunzhi.note.web.admin.query.GroupQuery;
import cn.cpoet.yunzhi.note.web.admin.service.GroupService;
import cn.cpoet.yunzhi.note.web.admin.vo.GroupVO;
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
@RequestMapping("/group")
@RequiredArgsConstructor
@Tag(name = "Group", description = "用户组管理")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/getGroupById")
    @HasPermission("sys:group:query")
    @Operation(summary = "查询用户组")
    public GroupVO getGroupById(@RequestBody @Validated IdQuery idQuery) {
        return groupService.getById(idQuery.getId());
    }

    @PostMapping("/listGroup")
    @HasPermission("sys:group:query")
    @Operation(summary = "分页查询用户组")
    public PageVO<GroupVO> listGroup(GroupQuery groupQuery) {
        return groupService.list(groupQuery);
    }

    @HasPermission("sys:group:insert")
    @PostMapping("/insertGroup")
    @Operation(summary = "新增用户组")
    public void insertGroup(@Validated(Insert.class) GroupDTO groupDTO) {
        groupService.insert(groupDTO);
    }

    @HasPermission("sys:group:update")
    @PostMapping("/updateGroup")
    @Operation(summary = "更新用户组")
    public void updateGroup(@Validated(Update.class) GroupDTO groupDTO) {
        groupService.update(groupDTO);
    }

    @HasPermission("sys:group:delete")
    @PostMapping("/deleteGroup")
    @Operation(summary = "删除用户组")
    public void deleteGroup(@Validated IdListQuery idListQuery) {
        groupService.deleteById(idListQuery.getId());
    }

    @PostMapping("/migrateMember")
    @Operation(summary = "用户组用户迁移")
    @HasPermission(value = {"sys:group:update", "sys:member:update"}, logic = LogicEnum.AND)
    public void migrateMember() {

    }
}
