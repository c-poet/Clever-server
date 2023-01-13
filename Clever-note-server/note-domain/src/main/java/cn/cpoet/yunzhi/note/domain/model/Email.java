package cn.cpoet.yunzhi.note.domain.model;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.constant.DbLenConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CPoet
 */
@Data
@Entity
@Table(name = "sys_email")
@Schema(title = "邮箱账户")
public class Email extends BaseModel {
    private static final long serialVersionUID = -1975411702598038631L;

    @Schema(title = "用户id")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Schema(title = "邮箱地址")
    @Column(name = "email", nullable = false, length = DbLenConst.EMAIL)
    private String email;

    @Schema(title = "密码")
    @Column(name = "password", nullable = false)
    private String password;
}
