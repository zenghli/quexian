package com.javaclimb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 小程序端登录的用户
 */
@Data
public class User extends PageBean{
    //尚未获取用户信息
    public static final String NOT_GET_USERINFO = "0";
    //已获取用户信息
    public static final String HAVE_GET_USERINFO = "1";

    //主键
    private Long id;
    //微信用户openid
    private String openid;
    //用户昵称
    private String nickName;
    //用户性别0未知，1男，2，女
    private Integer gender;
    //用户头像图片
    private String avatarUrl;
    //国家
    private String country;
    //省
    private String province;
    //市
    private String city;
    //是否已获取用户信息
    private String haveGetUserInfo;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
