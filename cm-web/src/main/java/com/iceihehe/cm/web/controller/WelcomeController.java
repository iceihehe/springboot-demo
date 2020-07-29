package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.dao.entity.SysUser;
import com.iceihehe.cm.service.SysUserService;
import com.iceihehe.cm.utils.common.AppType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(path = "/")
    public String welcome() {
        System.out.println(AppType.DIDI.getCode());
        SysUser u = sysUserService.getSysUserById(1);
        System.out.println(u.getName());
        return "Welcome!";
    }
}
