package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.utils.common.RespCode;
import com.iceihehe.cm.web.dto.GwResp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GwResp exception(Exception exception) {
        GwResp gwResp = new GwResp();
        if (exception instanceof BindException || exception instanceof MissingServletRequestParameterException) {
            gwResp.setCode(RespCode.PARAMETER_ERROR.getCode());
            logger.info(exception.getMessage());
            return gwResp;
        }
        gwResp.setCode(RespCode.SYSTEM_ERROR.getCode());
        logger.error(exception);
        return gwResp;
    }
}
