package cn.cpoet.yunzhi.note.web.admin.dto;

import cn.cpoet.clever.core.validator.CommValidatorRuleKeys;
import cn.cpoet.clever.core.validator.DynamicValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "添加用户信息")
public class MemberDTO implements Serializable {

    private static final long serialVersionUID = -3142268397238242523L;

    @Schema(title = "账号")
    @DynamicValidator(CommValidatorRuleKeys.MEMBER_ACCOUNT)
    private String account;

    @Schema(title = "密码")
    @DynamicValidator(CommValidatorRuleKeys.MEMBER_PASSWORD)
    private String password;

    @Schema(title = "用户组")
    @NotNull(message = "需要指定用户组")
    private Long groupId;

    @Schema(title = "过期时间")
    @Future(message = "过期时间不符合要求")
    private LocalDateTime expiredTime;
}
