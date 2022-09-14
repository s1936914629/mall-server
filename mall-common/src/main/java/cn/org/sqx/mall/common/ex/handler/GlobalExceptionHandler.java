package cn.org.sqx.mall.common.ex.handler;

import cn.org.sqx.mall.common.ex.ServiceException;
import cn.org.sqx.mall.common.web.JsonResult;
import cn.org.sqx.mall.common.web.State;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;

import java.util.List;

/**
 * 全局异常处理
 *
 * @auther: sqx
 * @Date: 2022-09-14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleServiceException(ServiceException ex) {
        return JsonResult.fail(ex.getState(), ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public JsonResult<Void> handleBindException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append("；");
            stringBuilder.append(fieldError.getDefaultMessage());
        }
        String message = stringBuilder.substring(1);
        return JsonResult.fail(State.ERR_BAD_REQUEST, message);
    }
}
