package org.aurora.springboot.shiro.filter.exception;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aurora.springboot.shiro.common.CommonConstant;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class ExceptionHandlerFilter extends HttpFilter {

    @Value(CommonConstant.SPRING_ERROR_PATH)
    private String springErrorPath;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            request.setAttribute(CommonConstant.REQUEST_ATTRIBUTE_EXCEPTION, e);
            throw e;
            // request.getRequestDispatcher(springErrorPath).forward(request, response);
        }
    }
}
