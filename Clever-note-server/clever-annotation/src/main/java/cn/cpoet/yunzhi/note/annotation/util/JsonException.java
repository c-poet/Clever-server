package cn.cpoet.yunzhi.note.annotation.util;

import cn.cpoet.yunzhi.note.annotation.exception.NoteException;

/**
 * @author CPoet
 */
public class JsonException extends NoteException {

    private static final long serialVersionUID = -4261933284887525312L;

    protected JsonException(String message, Object... params) {
        super(message, params);
    }

    protected JsonException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    protected JsonException(Throwable cause) {
        super(cause);
    }
}
