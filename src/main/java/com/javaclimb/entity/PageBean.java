package com.javaclimb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页实体类
 */
@Data
public class PageBean implements Serializable {
    private Integer page;

    private Integer limit;
}
