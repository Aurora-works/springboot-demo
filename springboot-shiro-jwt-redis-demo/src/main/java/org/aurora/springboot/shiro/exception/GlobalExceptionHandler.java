package org.aurora.springboot.shiro.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.aurora.springboot.shiro.common.Result;
import org.aurora.springboot.shiro.common.ResultStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未经身份验证
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public Result<?> unauthenticatedException(UnauthenticatedException e) {
        log.error(e.getMessage(), e);
        return Result.fail(ResultStatus.FAIL_UNAUTHENTICATED);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getClass().getSimpleName());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail();
    }
}
