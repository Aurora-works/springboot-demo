package org.aurora.springboot.shiro.common.dict;

/**
 * 用户类型 (对应系统数据字典表)
 */
public interface UserType {
    /**
     * 系统管理员
     */
    String ADMIN = "ADMIN";
    /**
     * 普通用户
     */
    String USER = "USER";
    /**
     * 系统留用
     */
    String TODO = "TODO";
}
