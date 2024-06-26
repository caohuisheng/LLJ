package com.llj.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class,Controller.class})
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public R<String> handleCustomException(CustomException ex){
        log.error("CustomException:{}",ex.getMessage());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler
    public R<String> handleDuplicateKeyException(DuplicateKeyException ex){
        log.error("DuplicateKeyException:{}",ex.getMessage());
        return R.error("DuplicateKeyException!");
    }

    @ExceptionHandler
    public R<String> handleOtherException(Exception ex){
        log.info("未知异常：{}",ex.toString());
        ex.printStackTrace();
        return R.error("未知异常！");
    }
}
