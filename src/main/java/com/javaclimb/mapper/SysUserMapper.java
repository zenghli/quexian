package com.javaclimb.mapper;

import com.javaclimb.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * 后台管理员mapper
 */
@Repository
public interface SysUserMapper {
    /**
     * 根据登录名查询实体
     */
    public SysUser getUserByUserName(String username);
}
