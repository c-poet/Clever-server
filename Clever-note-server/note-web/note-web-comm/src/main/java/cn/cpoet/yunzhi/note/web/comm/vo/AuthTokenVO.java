package cn.cpoet.yunzhi.note.web.comm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "认证结果", description = "携带认证Token")
public class AuthTokenVO implements Serializable {

    private static final long serialVersionUID = 1749295285192632701L;

    @Schema(title = "用户id")
    private Long uid;

    @Schema(title = "认证Token")
    private String token;
}
