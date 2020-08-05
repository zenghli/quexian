package com.javaclimb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.entity.GoodsType;
import com.javaclimb.mapper.GoodsTypeMapper;
import com.javaclimb.service.GoodsTypeService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import com.javaclimb.util.UpdateOrInsertResultDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public ReturnDataForLayui getList(GoodsType goodsType) {
        PageHelper.startPage(goodsType.getPage(),goodsType.getLimit());
        List<GoodsType> list = goodsTypeMapper.getList(goodsType);
        PageInfo<GoodsType> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list,info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ReturnData add(GoodsType goodsType) {
        int i = goodsTypeMapper.add(goodsType);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ReturnData delete(GoodsType goodsType) {
        int i = goodsTypeMapper.delete(goodsType);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ReturnData update(GoodsType goodsType) {
        int i = goodsTypeMapper.update(goodsType);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(GoodsType goodsType) {
        int i = goodsTypeMapper.updateState(goodsType);
        return UpdateOrInsertResultDeal.dealWith(i);
    }
}
