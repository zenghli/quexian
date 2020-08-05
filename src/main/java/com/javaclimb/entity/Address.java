package com.javaclimb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 收货地址
 */
@Data
public class Address extends PageBean {

    private Integer id;

    private String name;    //收货人姓名

    private String phone;   //手机号

    private String address; //收货地址

    private Integer uid;    //微信用户id

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    private Integer state;      //状态 1有效

}
