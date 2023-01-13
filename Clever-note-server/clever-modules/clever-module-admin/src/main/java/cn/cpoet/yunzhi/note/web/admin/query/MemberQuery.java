package cn.cpoet.yunzhi.note.web.admin.query;

import cn.cpoet.yunzhi.note.core.query.PageQuery;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "用户查询")
public class MemberQuery extends PageQuery {

    private static final long serialVersionUID = 8532896364909940647L;

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "姓名")
    private String name;

    @Schema(title = "昵称")
    private String nickName;

    @Schema(title = "账号")
    private String account;

    @Schema(title = "组id")
    private Long groupId;

    @Schema(title = "是否锁定")
    private Boolean locked;

    @Schema(title = "锁定过期时间开始")
    private LocalDateTime lockedExpiredStart;

    @Schema(title = "锁定过期时间终止")
    private LocalDateTime lockedExpiredEnd;

    @Schema(title = "状态")
    private CommStatus status;

    @Schema(title = "过期时间开始")
    private LocalDateTime expiredTimeStart;

    @Schema(title = "过期时间结束")
    private LocalDateTime expiredTimeEnd;

    @Schema(title = "是否内置")
    private Boolean isBuiltIn;
}
