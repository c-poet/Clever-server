package cn.cpoet.yunzhi.note.domain.exception;

import cn.cpoet.yunzhi.note.annotation.exception.NoteException;

/**
 * @author CPoet
 */
public class DomainException extends NoteException {

    private static final long serialVersionUID = -8194317644491746932L;

    public DomainException(String message, Object... params) {
        super(message, params);
    }

    public DomainException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}
