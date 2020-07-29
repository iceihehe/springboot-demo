package com.iceihehe.cm.dao.mapper;

import com.iceihehe.cm.dao.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysUserMapper {
    List<SysUser> getSysUsers();

    SysUser getSysUserById(int id);
}
