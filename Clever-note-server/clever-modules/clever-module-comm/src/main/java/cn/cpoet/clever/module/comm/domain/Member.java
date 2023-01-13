package cn.cpoet.clever.module.comm.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author CPoet
 */
@Data
@Entity
@Schema(title = "人员信息")
@Table(name = "sys_member")
public class Member extends BaseModel {

    private static final long serialVersionUID = -3355168045193335667L;

    @Schema(title = "用户姓名")
    @Column(name = "name", length = DbLenConst.STAFF_NAME)
    private String name;

    @Schema(title = "昵称")
    @Column(name = "nickName", length = DbLenConst.L50)
    private String nickName;

    @Schema(title = "用户账号")
    @Column(name = "account", length = DbLenConst.ACCOUNT, unique = true)
    private String account;

    @Schema(title = "头像地址")
    @Column(name = "avatar", length = DbLenConst.AVATAR)
    private String avatar;

    @Schema(title = "密码摘要")
    @Column(name = "password", length = DbLenConst.L32)
    private String password;

    @Schema(title = "密码摘要盐值")
    @Column(name = "salt", length = DbLenConst.L32)
    private String salt;

    @Schema(title = "用户组id")
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Schema(title = "个人简介")
    @Column(name = "summary", length = DbLenConst.L512)
    private String summary;

    @Schema(title = "账号是否锁定")
    @Column(name = "locked", nullable = false)
    private Boolean locked;

    @Schema(title = "账号锁定过期时间")
    @Column(name = "locked_expired")
    private LocalDateTime lockedExpired;

    @Schema(title = " 状态")
    @Column(name = "status", nullable = false)
    private CommStatus status;

    @Schema(title = "账号过期时间")
    @Column(name = "expired_time", nullable = false)
    private LocalDateTime expiredTime;

    @Schema(title = "是否内置")
    @Column(name = "is_built_in", nullable = false)
    private Boolean isBuiltIn;
}
