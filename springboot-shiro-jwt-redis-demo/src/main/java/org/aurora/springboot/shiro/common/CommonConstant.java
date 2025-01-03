package org.aurora.springboot.shiro.common;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;

import java.time.Duration;

/**
 * 公共常量
 */
public interface CommonConstant {

    /**
     * Token 在 RequestHeader 中的名称
     */
    String TOKEN_HEADER_NAME = "Authorization";

    /**
     * Token 在 Redis 中的前缀
     */
    String TOKEN_REDIS_PREFIX = "token";

    /**
     * Token 过期时间
     */
    Duration TOKEN_EXPIRE_TIME = Duration.ofHours(3);

    /**
     * Exception 在 RequestAttribute 中的名称
     */
    String REQUEST_ATTRIBUTE_EXCEPTION = "exception";

    /**
     * Spring Error Controller
     *
     * @see BasicErrorController
     */
    String SPRING_ERROR_PATH = "${server.error.path:${error.path:/error}}";

    /**
     * Shiro 使用的哈希算法名称
     */
    String SHIRO_HASH_ALGORITHM_NAME = "SHA-256";

    /**
     * Shiro 重复哈希迭代的次数
     */
    int SHIRO_HASH_ITERATIONS = 1;
}
