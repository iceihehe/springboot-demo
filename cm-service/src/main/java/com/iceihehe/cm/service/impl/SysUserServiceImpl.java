package com.iceihehe.cm.service.impl;


import com.iceihehe.cm.dao.entity.SysUser;
import com.iceihehe.cm.dao.mapper.SysUserMapper;
import com.iceihehe.cm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getSysUsers(){
        return sysUserMapper.getSysUsers();
    }

    @Override
    public SysUser getSysUserById(int id) {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public SysUser getSysUserByUsername(String username) {
        return sysUserMapper.getSysUserByUsername(username);
    }
}
