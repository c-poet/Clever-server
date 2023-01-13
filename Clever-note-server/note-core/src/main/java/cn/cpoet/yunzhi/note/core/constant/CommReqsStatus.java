package cn.cpoet.yunzhi.note.core.constant;

import cn.cpoet.yunzhi.note.api.constant.InfoLevel;
import cn.cpoet.yunzhi.note.api.constant.Status;
import lombok.RequiredArgsConstructor;

/**
 * 常用状态定义
 * <div>
 *     状态码说明：
 *     <p>code = 0: 代表成功</p>
 *     <p>code = -1: 系统级错误/未知错误</p>
 *     <p>code < -1: 错误情况</p>
 *     <p>1000 <= code <= 2000: 公共状态码预留</p>
 *     <p>code > 2000 : 其它模块自用</p>
 * </div>
 *
 * @author CPoet
 */
@RequiredArgsConstructor
public enum CommReqsStatus implements Status {
    /**
     * OK
     */
    SUCCESS(0, "success", InfoLevel.SUCCESS),

    /**
     * 参数校验失败
     */
    PARAMETER_CHECK_FAILED(1000, "参数校验失败", InfoLevel.WARN),

    /**
     * 无权访问
     */
    PERMISSION_DENIED(1002, "无权访问", InfoLevel.WARN),

    /**
     * 资源不存在
     */
    NONEXISTENT_RESOURCE(1003, "资源不存在", InfoLevel.WARN),

    /**
     * 拒绝访问
     */
    ACCESS_DENIED(1004, "拒绝访问", InfoLevel.WARN),

    /**
     * 认证失败
     */
    AUTHENTICATION_FAILED(1005, "认证失败", InfoLevel.WARN),

    /**
     * 用户未登录
     */
    NOT_LOGIN(1006, "用户未登录", InfoLevel.WARN),

    /**
     * 用户状态异常
     */
    USER_STATUS_ERROR(1007, "用户状态异常", InfoLevel.WARN),

    /**
     * 无效的请求参数
     */
    INVALID_PARAMETER(1008, "无效的请求参数", InfoLevel.WARN),

    /**
     * 拒绝操作
     */
    DENY_ACTION(1009, "拒绝操作", InfoLevel.WARN),

    /**
     * 系统错误
     */
    SYS_ERROR(-1, "系统错误", InfoLevel.ERROR);

    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态信息
     */
    private final String msg;

    /**
     * 等级
     */
    private final InfoLevel level;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return msg;
    }

    @Override
    public InfoLevel level() {
        return level;
    }
}
