package cn.cpoet.clever.core.exception;

/**
 * Feign调用异常
 *
 * @author CPoet
 */
public class FeignException extends CoreException {

    private static final long serialVersionUID = 2025579838128664651L;

    public FeignException(String message, Object... params) {
        super(message, params);
    }

    public FeignException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public FeignException(Throwable cause) {
        super(cause);
    }
}
