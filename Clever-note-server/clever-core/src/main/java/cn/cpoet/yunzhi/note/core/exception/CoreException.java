package cn.cpoet.yunzhi.note.core.exception;

import cn.cpoet.yunzhi.note.annotation.exception.NoteException;

/**
 * @author CPoet
 */
public class CoreException extends NoteException {

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
