package cn.cpoet.yunzhi.note.core.component;

import cn.cpoet.yunzhi.note.core.exception.ReqsException;
import cn.cpoet.yunzhi.note.core.constant.CommReqsStatus;
import cn.cpoet.yunzhi.note.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author CPoet
 */
@Slf4j
@ControllerAdvice
public class WebErrorAdvice {
    /**
     * 请求异常处理
     */
    @ExceptionHandler(ReqsException.class)
    @ResponseBody
    public ResultVO<?> reqsException(ReqsException e) {
        if (log.isDebugEnabled()) {
            log.debug("系统请求异常: {}, 返回状态: {}", e.getMessage(), e.getStatus(), e);
        } else {
            log.info("系统请求异常: {}, 返回状态: {}", e.getMessage(), e.getStatus());
        }
        return ResultVO.of(e.getStatus());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO<?> bindException(BindException e) {
        if (log.isDebugEnabled()) {
            log.debug("参数验证错误：{}", e.getMessage(), e);
        }
        if (e.getFieldErrorCount() == 0) {
            return ResultVO.of(CommReqsStatus.INVALID_PARAMETER);
        }
        Map<String, String> result = new HashMap<>(e.getFieldErrorCount());
        for (FieldError fieldError : e.getFieldErrors()) {
            result.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResultVO.of(CommReqsStatus.INVALID_PARAMETER, result);
    }

    /**
     * 全局异常/未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultVO<?> exception(Exception e) {
        log.warn("系统异常：{}", e.getMessage(), e);
        return ResultVO.of(CommReqsStatus.SYS_ERROR);
    }
}
