package org.aurora.springboot.shiro.common;

import lombok.Getter;

/**
 * 自定义返回状态
 */
@Getter
public enum ResultStatus {

    SUCCESS
            (0, ""),
    FAIL
            (-1, "系统错误"),
    FAIL_UNAUTHENTICATED
            (-100001, "未经身份验证"),
    FAIL_AUTHENTICATION
            (-100002, "身份验证异常");

    private final Integer code;
    private final String defaultMessage;

    ResultStatus(Integer code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
