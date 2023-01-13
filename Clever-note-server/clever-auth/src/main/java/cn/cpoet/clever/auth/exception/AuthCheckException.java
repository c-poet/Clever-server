package cn.cpoet.clever.auth.exception;


/**
 * 认证检查异常
 *
 * @author CPoet
 */
public class AuthCheckException extends AuthException {

    private static final long serialVersionUID = 785034433738180399L;

    public AuthCheckException(String message) {
        super(message);
    }

    public AuthCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthCheckException(Throwable cause) {
        super(cause);
    }
}
