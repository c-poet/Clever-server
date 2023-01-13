package cn.cpoet.clever.auth.exception;

import cn.cpoet.clever.exception.CleverException;

/**
 * 认证异常
 *
 * @author CPoet
 */
public class AuthException extends CleverException {

    private static final long serialVersionUID = -8600517080838446289L;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
