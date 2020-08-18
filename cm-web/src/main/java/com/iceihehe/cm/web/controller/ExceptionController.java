package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.utils.common.RespCode;
import com.iceihehe.cm.web.dto.GwResp;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GwResp exception(Exception exception) {
        GwResp gwResp = new GwResp();
        if (exception instanceof BindException || exception instanceof MissingServletRequestParameterException) {
            gwResp.setCode(RespCode.PARAMETER_ERROR.getCode());
            return gwResp;
        }
        gwResp.setCode(RespCode.SYSTEM_ERROR.getCode());
        return gwResp;
    }
}
