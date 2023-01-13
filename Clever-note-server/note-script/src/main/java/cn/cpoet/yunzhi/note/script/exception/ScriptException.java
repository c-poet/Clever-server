package cn.cpoet.yunzhi.note.script.exception;

import cn.cpoet.yunzhi.note.api.exception.NoteException;

/**
 * @author CPoet
 */
public class ScriptException extends NoteException {

    private static final long serialVersionUID = -880091778749219443L;

    protected ScriptException(String message, Object... params) {
        super(message, params);
    }

    protected ScriptException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    protected ScriptException(Throwable cause) {
        super(cause);
    }
}
