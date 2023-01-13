package cn.cpoet.clever.module.comm.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseRecordModel;
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
@Schema(title = "用户-角色")
@Table(name = "sys_member_role")
public class MemberRole extends BaseRecordModel {

    private static final long serialVersionUID = 5515443327995235294L;

    @Schema(title = "用户id")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Schema(title = "角色id")
    @Column(name = "role_id", nullable = false)
    private Long roleId;
}
