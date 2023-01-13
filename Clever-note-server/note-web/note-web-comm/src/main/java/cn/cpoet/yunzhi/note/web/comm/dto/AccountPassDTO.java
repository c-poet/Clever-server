package cn.cpoet.yunzhi.note.web.comm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户名密码信息")
public class AccountPassDTO implements Serializable {

    private static final long serialVersionUID = 1721884966526484148L;

    @Schema(title = "账号")
    @NotEmpty(message = "账号不能为空")
    private String account;

    @Schema(title = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Schema(title = "验证码")
    private String captcha;

    @Schema(title = "操作系统信息")
    private String os;

    @Schema(title = "终端分辨率")
    private String screen;
}
