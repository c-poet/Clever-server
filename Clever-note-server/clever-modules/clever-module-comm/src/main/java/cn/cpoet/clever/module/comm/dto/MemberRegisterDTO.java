package cn.cpoet.yunzhi.note.web.comm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户注册")
public class MemberRegisterDTO implements Serializable {

    private static final long serialVersionUID = 5534133000847535578L;

}
