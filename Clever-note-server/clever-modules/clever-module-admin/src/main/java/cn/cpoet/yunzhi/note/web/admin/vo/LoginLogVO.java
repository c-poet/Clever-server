package cn.cpoet.yunzhi.note.web.admin.vo;

import cn.cpoet.yunzhi.note.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.constant.LoginType;
import cn.cpoet.yunzhi.note.domain.constant.LogoutType;
import cn.cpoet.yunzhi.note.domain.model.LoginLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "登录日志")
public class LoginLogVO implements Serializable {

    private static final long serialVersionUID = 4850342504974922491L;

    @Schema(title = "日志id")
    private Long id;

    @Schema(title = "用户id")
    private Long memberId;

    @Schema(title = "登录账号")
    private String account;

    @Schema(title = "生成的token")
    private String token;

    @Schema(title = "token特征值")
    private String tokenMd5;

    @Schema(title = "登录类型")
    private LoginType loginType;

    @Schema(title = "登出类型")
    private LogoutType logoutType;

    @Schema(title = "登录IP地址")
    private String ipAddr;

    @Schema(title = "UA信息")
    private String userAgent;

    @Schema(title = "操作系统")
    private String os;

    @Schema(title = "分辨率信息")
    private String screen;

    @Schema(title = "登录时间")
    private LocalDateTime loginTime;

    @Schema(title = "登出时间")
    private LocalDateTime logoutTime;

    @Schema(title = "扩展字段01")
    private String ext01;

    @Schema(title = "扩展字段02")
    private String ext02;

    @Schema(title = "扩展字段03")
    private String ext03;

    public LoginLogVO copyOf(LoginLog loginLog) {
        return BeanUtil.copyProperties(loginLog, this);
    }
}
