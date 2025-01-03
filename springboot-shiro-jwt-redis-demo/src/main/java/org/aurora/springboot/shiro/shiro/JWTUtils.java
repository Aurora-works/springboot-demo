package org.aurora.springboot.shiro.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTUtils {

    @Value("${custom-config.jwt.secret}")
    private String jwtSecret;

    /**
     * 校验 token 是否正确
     */
    public boolean verify(String token, String subject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWT.require(algorithm)
                    .withSubject(subject)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 获取 token 中 claim 为 sub 的值
     */
    public static String getSubject(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成 token
     */
    public String sign(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(Instant.now())
                // .withExpiresAt(Instant.now().plus(CommonConstant.JWT_EXPIRE_TIME))
                .sign(algorithm);
    }
}
