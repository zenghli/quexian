package com.javaclimb.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单基本信息
 */
@Data
public class Orders extends PageBean {

    private Integer id;

    private BigDecimal totalPrice;  //订单金额

    private Long uid;           //用户姓名

    private String orderSn;     //订单编号

    private Integer state;      //订单状态

    private Integer addressId; //收货人地址id

    private Address address;    //收货人对象

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    private String nickName;    //微信用户昵称

    private String avatarUrl;   //头像地址

    private List<OrdersDetail> ordersDetailList;
}
