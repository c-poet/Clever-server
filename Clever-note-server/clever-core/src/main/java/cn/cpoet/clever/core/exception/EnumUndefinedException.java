package cn.cpoet.clever.core.exception;

import cn.cpoet.clever.exception.CleverException;

/**
 * 枚举未定义异常
 *
 * @author CPoet
 */
public class EnumUndefinedException extends CleverException {

    private static final long serialVersionUID = 7014306719832937949L;

    public final static EnumUndefinedException INSTANCE = new EnumUndefinedException("undefined enum type.");

    public static EnumUndefinedException getInstance() {
        return INSTANCE;
    }

    public EnumUndefinedException(String message, Object... params) {
        super(message, params);
    }

    public EnumUndefinedException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public EnumUndefinedException(Throwable cause) {
        super(cause);
    }
}
