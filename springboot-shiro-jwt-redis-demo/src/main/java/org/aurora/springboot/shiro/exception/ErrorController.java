package org.aurora.springboot.shiro.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.aurora.springboot.shiro.common.CommonConstant;
import org.aurora.springboot.shiro.common.Result;
import org.aurora.springboot.shiro.common.ResultStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(CommonConstant.SPRING_ERROR_PATH)
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping
    public Result<?> error(HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE_EXCEPTION);

        if (throwable instanceof ServletException) {
            throwable = ((ServletException) throwable).getRootCause();
        }

        ResultStatus resultStatus;
        if (throwable instanceof AuthenticationException) {
            resultStatus = ResultStatus.FAIL_AUTHENTICATION;
        } else {
            resultStatus = ResultStatus.FAIL;
            log.error(throwable.getMessage(), throwable);
        }

        return Result.fail(resultStatus);
    }
}
