package com.javaclimb.mapper;

import com.javaclimb.entity.GoodsType;

import java.util.List;

/**
 * 分类
 */
public interface GoodsTypeMapper {
    List<GoodsType> getList(GoodsType goodsType);
    int add(GoodsType goodsType);
    int delete(GoodsType goodsType);
    int update(GoodsType goodsType);
    int updateState(GoodsType goodsType);
}
