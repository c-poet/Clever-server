package cn.cpoet.clever.exception;

/**
 * 所有的系统业务异常均转换为非受检异常，未进行显示处理的将由全局异常处理类进行处理
 *
 * @author CPoet
 */
public class CleverException extends RuntimeException {

    private static final long serialVersionUID = 965306345532937785L;

    public CleverException(String message) {
        super(message);
    }

    public CleverException(String message, Throwable cause) {
        super(message, cause);
    }

    public CleverException(Throwable cause) {
        super(cause);
    }
}
