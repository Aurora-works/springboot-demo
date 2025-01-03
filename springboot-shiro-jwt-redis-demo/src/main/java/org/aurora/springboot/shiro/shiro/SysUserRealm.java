package org.aurora.springboot.shiro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.*;

public class SysUserRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private ShiroUtils shiroUtils;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        Long userId = Long.valueOf(shiroUtils.getSubject(token));
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        // 根据用户id获取用户角色和权限信息
        if (userId.equals(1L)) {
            roles.addAll(Arrays.asList("ADMIN", "SYS_MGR"));
            permissions.addAll(Arrays.asList("sys_user:create", "sys_user:update", "sys_user:delete", "sys_user:read"));
        }
        // end

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = ((JWTToken) authenticationToken).getToken();
        String subject = shiroUtils.getSubject(token); // userId
        // 校验 token 是否正确
        if (subject == null || !shiroUtils.verify(token, subject)) {
            throw new IncorrectCredentialsException(token);
        }
        // 校验 token 是否过期
        if (shiroUtils.isTokenExpired(token, subject)) {
            throw new AuthenticationException(token);
        }
        // 刷新 token 生命周期
        shiroUtils.refreshToken(token, subject);
        return new SimpleAuthenticationInfo(
                token,
                token,
                getName()
        );
    }
}
