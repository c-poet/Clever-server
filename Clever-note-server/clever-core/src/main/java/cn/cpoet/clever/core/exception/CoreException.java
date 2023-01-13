package cn.cpoet.clever.core.exception;

import cn.cpoet.clever.exception.CleverException;

/**
 * @author CPoet
 */
public class CoreException extends CleverException {

    private static final long serialVersionUID = 4742544579914328653L;

    public CoreException(String message, Object... params) {
        super(message, params);
    }

    public CoreException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }
}
