package com.iceihehe.cm.dao.mapper;

import com.iceihehe.cm.dao.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    List<SysUser> getSysUsers();

    SysUser getSysUserById(int id);
    SysUser getSysUserByUsername(String username);
}
