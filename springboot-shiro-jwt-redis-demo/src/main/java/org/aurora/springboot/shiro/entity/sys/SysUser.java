package org.aurora.springboot.shiro.entity.sys;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.springboot.shiro.entity.BaseEntity;

/**
 * 系统用户表
 */
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private String username;

    private String password;

    private String salt;

    private String status;

    private String userType;
}
