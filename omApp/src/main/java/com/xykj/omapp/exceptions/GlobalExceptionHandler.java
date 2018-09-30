package com.xykj.omapp.exceptions;

import com.xykj.ombase.utils.error.ErrorInfo;
import com.xykj.ombase.utils.error.OceanException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ocean
 * @Title: GlobalExceptionHandler
 * @ProjectName omProject
 * @Description:  => 统一异常处理
 * @date 2018/9/24下午1:07
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo jsonError(HttpServletRequest req, Exception e){
        return ErrorInfo.builder()
                .code(ErrorInfo.ERROR)
                .msg(e.getMessage())
                .url(req.getRequestURI())
                .build();
    }

}
