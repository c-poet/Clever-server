package cn.cpoet.yunzhi.note.web.comm.vo;

import cn.cpoet.clever.core.util.BeanUtil;
import cn.cpoet.yunzhi.note.domain.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author CPoet
 */
@Data
@Schema(title = "用户基本信息")
public class MemberInfoVO implements Serializable {

    private static final long serialVersionUID = 4147387115952667785L;

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户账号")
    private String account;

    @Schema(title = "昵称")
    private String nickName;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "用户组信息")
    private Group group;

    @Schema(title = "个人简介")
    private String summary;

    public MemberInfoVO copyOf(Member member) {
        return BeanUtil.copyProperties(member, this);
    }

    @Data
    public static class Group implements Serializable {

        private static final long serialVersionUID = -7182271360455276792L;

        @Schema(title = "id")
        private Long id;

        @Schema(title = "全称")
        private String name;

        @Schema(title = "简称")
        private String shortName;
    }
}
