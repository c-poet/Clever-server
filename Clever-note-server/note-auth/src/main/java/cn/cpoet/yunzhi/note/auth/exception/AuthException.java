package cn.cpoet.yunzhi.note.auth.exception;

import cn.cpoet.yunzhi.note.api.exception.NoteException;

/**
 * 认证异常
 *
 * @author CPoet
 */
public class AuthException extends NoteException {

    private static final long serialVersionUID = -8600517080838446289L;

    public AuthException(String message, Object... params) {
        super(message, params);
    }

    public AuthException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
