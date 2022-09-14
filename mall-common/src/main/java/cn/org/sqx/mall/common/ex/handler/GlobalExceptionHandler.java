package cn.org.sqx.mall.common.ex.handler;

import cn.org.sqx.mall.common.ex.ServiceException;
import cn.org.sqx.mall.common.web.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @auther: sqx
 * @Date: 2022-09-14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleServiceException(ServiceException ex){
        return JsonResult.fail(ex.getState(), ex.getMessage());
    }
}
