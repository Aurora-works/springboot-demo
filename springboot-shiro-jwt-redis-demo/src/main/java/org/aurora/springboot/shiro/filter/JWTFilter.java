package org.aurora.springboot.shiro.filter;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.aurora.springboot.shiro.common.CommonConstant;
import org.aurora.springboot.shiro.shiro.JWTToken;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 创建 JWTToken
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return new JWTToken(httpRequest.getHeader(CommonConstant.TOKEN_HEADER_NAME));
    }

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            executeLogin(request, response);
        }
        return true;
    }

    /**
     * 是否尝试登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(CommonConstant.TOKEN_HEADER_NAME);
        return token != null;
    }

    /**
     * 使用 JWTToken 登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        SecurityUtils.getSubject().login(createToken(request, response));
        return true;
    }
}
