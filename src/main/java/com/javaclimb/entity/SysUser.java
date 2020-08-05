package com.javaclimb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 后台管理员
 */
@Data
public class SysUser extends PageBean{

    private Integer id;     //主键
    private String username;    //登录名
    private String password;    //密码
    private String name;        //姓名
    private Integer sex;        //性别
    private Integer age;        //年龄
    private Date createTime;    //创建时间
    private Date updateTime;    //修改时间

}
