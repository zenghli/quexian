package com.javaclimb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 分类
 */
@Data
public class GoodsType extends PageBean {
    private Integer id;     //主键
    private String name;        //类别名称
    private Integer state;        //状态 1 有效
    private Date createTime;    //创建时间
    private Date updateTime;    //修改时间
}
