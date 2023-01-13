package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.yunzhi.note.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户信息")
public class MemberVO implements Serializable {

    private static final long serialVersionUID = -7742065638975251124L;

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户姓名")
    private String name;

    @Schema(title = "昵称")
    private String nickName;

    @Schema(title = "用户账号")
    private String account;

    @Schema(title = "头像地址")
    private String avatar;

    @Schema(title = "个人简介")
    private String summary;

    @Schema(title = "账号是否锁定")
    private Boolean locked;

    @Schema(title = "账号锁定过期时间")
    private LocalDateTime lockedExpired;

    @Schema(title = " 状态")
    private CommStatus status;

    @Schema(title = "账号过期时间")
    private LocalDateTime expiredTime;

    @Schema(title = "是否内置")
    private Boolean isBuiltIn;

    @Schema(title = "用户组")
    private Group group;

    @Schema(title = "用户角色")
    private List<Role> roles;

    @Schema(title = "创建时间")
    private LocalDateTime createdTime;

    @Schema(title = "更新时间")
    private LocalDateTime updatedTime;

    public MemberVO copyOf(Member member) {
        return BeanUtil.copyProperties(member, this);
    }

    @Data
    public static class Group implements Serializable {

        private static final long serialVersionUID = -7900914502867698948L;

        @Schema(title = "组id")
        private Long id;

        @Schema(title = "组名")
        private String name;
    }

    @Data
    public static class Role implements Serializable {

        private static final long serialVersionUID = 2705639519248595400L;

        @Schema(title = "角色id")
        private Long id;

        @Schema(title = "角色名")
        private String name;

        @Schema(title = "角色标识")
        private String code;
    }
}
