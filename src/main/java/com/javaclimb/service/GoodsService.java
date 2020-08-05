package com.javaclimb.service;

import com.javaclimb.entity.Goods;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;

/**
 * 图书service接口
 */
public interface GoodsService {
    ReturnDataForLayui getList(Goods goods);
    ReturnData add(Goods goods);
    ReturnData delete(Goods goods);
    ReturnData update(Goods goods);
    ReturnData updateState(Goods goods);
}
