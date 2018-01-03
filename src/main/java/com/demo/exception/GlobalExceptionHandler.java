package com.demo.exception;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = BaseException.class)
    public ResponseUtils<String> baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---BaseException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new ResponseUtils<String>(StatusCode.REQUESTERROR.getName(),StatusCode.REQUESTERROR.getIndex(),e.getMessage());
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseUtils<String> defaultErrorHandler(HttpServletRequest req, Exception e)  throws Exception  {
        logger.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
    		e.printStackTrace();
        return new ResponseUtils<String>(StatusCode.REQUESTERROR.getName(),StatusCode.REQUESTERROR.getIndex(),e.getMessage());
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtils<String> handleMethodArgumentNotValidExceptionHandler(HttpServletRequest req,MethodArgumentNotValidException e)  throws Exception  {
    	logger.error("---handleMethodArgumentNotValidException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        BindingResult result = e.getBindingResult();
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : result.getAllErrors()) {
            String field = error.getCode();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            sb.append(" , ");
            sb.append(message);
        }
        return new ResponseUtils<String>(StatusCode.REQUESTERROR.getName(),StatusCode.REQUESTERROR.getIndex(),sb.toString());

    }
    
    
   
    
    
   
    
    
}