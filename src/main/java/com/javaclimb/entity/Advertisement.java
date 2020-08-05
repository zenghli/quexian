package com.javaclimb.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 轮播图
 */
@Data
public class Advertisement extends PageBean {

    private Integer id;     //主键

    private String name;    //名称

    private String coverImg;  //图片

    private Integer goodsId;     //图书id

    private String goodsName;    //分类名称

    private Integer state;      //状态

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间
}
