package cn.cpoet.yunzhi.note.annotation.exception;

import cn.cpoet.yunzhi.note.annotation.util.StringFormatUtil;

/**
 * 所有的系统业务异常均转换为非受检异常，未进行显示处理的将由全局异常处理类进行处理
 *
 * @author CPoet
 */
public class NoteException extends RuntimeException {

    private static final long serialVersionUID = 965306345532937785L;

    protected NoteException(String message, Object... params) {
        super(StringFormatUtil.format(message, params));
        if (params.length > 0 && params[params.length - 1] instanceof Throwable) {
            initCause((Throwable) params[params.length - 1]);
        }
    }

    protected NoteException(String message, Throwable cause, Object... params) {
        super(StringFormatUtil.format(message, params), cause);
    }

    protected NoteException(Throwable cause) {
        super(cause);
    }
}
