package com.javaclimb.mapper;

import com.javaclimb.entity.User;

import java.util.List;

public interface UserMapper {

    /**插入微信用户信息*/
    int insertUser(User user);

    /**更新微信部分的用户信息*/
    int updateUserOfWx(User user);

    /**更新用户头像，同时更新用户信息是否完善*/
    int updateUserAvatarAndFlag(User user);

    /**根据openid查询用户*/
    User selectUserByOpenid(String openid);

    /**根据主键查询*/
    List<User> getList(User user);
}
