package com.iceihehe.cm.service;


import com.iceihehe.cm.dao.entity.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> getSysUsers();
    SysUser getSysUserById(int id);
    SysUser getSysUserByUsername(String username);
}
