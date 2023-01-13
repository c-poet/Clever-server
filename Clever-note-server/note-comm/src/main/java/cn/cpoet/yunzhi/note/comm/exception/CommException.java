package cn.cpoet.yunzhi.note.comm.exception;

import cn.cpoet.yunzhi.note.annotation.exception.NoteException;

/**
 * @author CPoet
 */
public class CommException extends NoteException {

    private static final long serialVersionUID = 113458662730898952L;

    protected CommException(String message, Object... params) {
        super(message, params);
    }

    protected CommException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    protected CommException(Throwable cause) {
        super(cause);
    }
}
