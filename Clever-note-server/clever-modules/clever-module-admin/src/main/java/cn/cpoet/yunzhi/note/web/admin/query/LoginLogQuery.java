package cn.cpoet.yunzhi.note.web.admin.query;

import cn.cpoet.yunzhi.note.comm.iquery.annotation.Between;
import cn.cpoet.yunzhi.note.comm.iquery.annotation.Eq;
import cn.cpoet.yunzhi.note.comm.iquery.annotation.Like;
import cn.cpoet.yunzhi.note.comm.iquery.constant.LikeWays;
import cn.cpoet.yunzhi.note.core.query.PageQuery;
import cn.cpoet.yunzhi.note.domain.constant.LoginType;
import cn.cpoet.yunzhi.note.domain.constant.LogoutType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CPoet
 */
@Data
@Schema(title = "登录日志查询")
public class LoginLogQuery extends PageQuery {

    private static final long serialVersionUID = -8359976356537518287L;

    @Eq
    @Schema(title = "人员id")
    private Long memberId;

    @Like(way = LikeWays.RIGHT)
    @Schema(title = "登录账号")
    private String account;

    @Eq
    @Schema(title = "登录类型")
    private LoginType loginType;

    @Eq
    @Schema(title = "登出类型")
    private LogoutType logoutType;

    @Between
    @Schema(title = "登录时间开始")
    private LocalDateTime loginTimeStart;

    @Between(left = false)
    @Schema(title = "登录时间结束")
    private LocalDateTime loginTimeEnd;
}
