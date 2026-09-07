package com.example.exception;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务端出错了",e);
        return Result.error("出错了");
    }

    @ExceptionHandler
    public Result handleDuplicateException(DuplicateKeyException e) {
        log.error("服务端出错了",e);
        String message = e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String message1=message.substring(i);
        String[] arr=message1.split(" ");
        return Result.error(arr[2]+"已存在");
    }
}
