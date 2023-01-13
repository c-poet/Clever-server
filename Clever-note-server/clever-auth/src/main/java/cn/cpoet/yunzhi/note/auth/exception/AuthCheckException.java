package cn.cpoet.yunzhi.note.auth.exception;


/**
 * 认证检查异常
 *
 * @author CPoet
 */
public class AuthCheckException extends AuthException {

    private static final long serialVersionUID = 785034433738180399L;

    public AuthCheckException(String message, Object... params) {
        super(message, params);
    }

    public AuthCheckException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public AuthCheckException(Throwable cause) {
        super(cause);
    }
}
