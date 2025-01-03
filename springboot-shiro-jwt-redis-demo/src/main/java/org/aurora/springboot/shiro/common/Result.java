package org.aurora.springboot.shiro.common;

/**
 * 自定义返回结果
 */
public record Result<T>(
        Integer code,
        String message,
        T data) {

    public static <T> Result<T> success(T data) {
        ResultStatus status = ResultStatus.SUCCESS;
        return new Result<>(
                status.getCode(),
                status.getDefaultMessage(),
                data);
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> fail(String message) {
        return new Result<>(ResultStatus.FAIL.getCode(), message, null);
    }

    public static Result<Object> fail() {
        ResultStatus status = ResultStatus.FAIL;
        return new Result<>(status.getCode(), status.getDefaultMessage(), null);
    }

    public static Result<Object> fail(ResultStatus status) {
        return new Result<>(status.getCode(), status.getDefaultMessage(), null);
    }

    public static Result<Object> fail(ResultStatus status, String message) {
        return new Result<>(status.getCode(), message, null);
    }
}
