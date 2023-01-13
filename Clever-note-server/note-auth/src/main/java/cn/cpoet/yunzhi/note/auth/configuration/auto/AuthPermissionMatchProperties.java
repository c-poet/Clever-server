package cn.cpoet.yunzhi.note.auth.configuration.auto;

import cn.cpoet.yunzhi.note.api.constant.LogicEnum;
import lombok.Data;

import java.util.List;

/**
 * 认证信息权限控制
 *
 * @author CPoet
 */
@Data
public class AuthPermissionMatchProperties {
    /**
     * 逻辑关系
     */
    private LogicEnum logic = LogicEnum.OR;

    /**
     * 访问具有的角色
     */
    private List<String> roles;

    /**
     * 访问具有的权限
     */
    private List<String> permissions;
}
