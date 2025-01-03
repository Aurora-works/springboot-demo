package org.aurora.springboot.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aurora.springboot.shiro.common.Result;
import org.aurora.springboot.shiro.shiro.ShiroUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/create")
    @RequiresPermissions("sys_user:create")
    public Result<?> testCreate() {
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> testUpdate() {
        ShiroUtils.checkPermission("sys_user:update");
        return Result.success();
    }

    @PostMapping("/r")
    @RequiresAuthentication
    public Result<?> testR() {
        return Result.success();
    }
}
