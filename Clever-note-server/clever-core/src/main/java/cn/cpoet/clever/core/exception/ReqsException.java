package cn.cpoet.clever.core.exception;

import cn.cpoet.clever.exception.CleverException;
import cn.cpoet.yunzhi.note.annotation.constant.InfoLevel;
import cn.cpoet.yunzhi.note.annotation.constant.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 请求异常
 *
 * @author CPoet
 */
@Getter
public class ReqsException extends CleverException {

    private static final long serialVersionUID = -7213730675302301174L;

    private final Status status;

    public ReqsException(Status status) {
        super(status.message());
        this.status = status;
    }

    public ReqsException(Status status, String message, Object... params) {
        super(message, params);
        this.status = new InlineStatus(status.code(), message, status.level());
    }

    public ReqsException(Status status, String message, Throwable cause, Object... params) {
        super(message, cause, params);
        this.status = new InlineStatus(status.code(), message, status.level());
    }

    @ToString
    @RequiredArgsConstructor
    private static class InlineStatus implements Status {
        private final int code;
        private final String message;
        private final InfoLevel level;

        @Override
        public int code() {
            return code;
        }

        @Override
        public String message() {
            return message;
        }

        @Override
        public InfoLevel level() {
            return level;
        }
    }
}
