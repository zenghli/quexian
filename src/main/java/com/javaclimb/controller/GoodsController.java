package com.javaclimb.controller;

import com.javaclimb.entity.Goods;
import com.javaclimb.service.GoodsService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理控制层
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "goods/list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Goods goods){
        return goodsService.getList(goods);
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "goods/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Goods goods){
        return goodsService.add(goods);
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "goods/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Goods goods){
        return goodsService.update(goods);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Goods goods){
        return goodsService.updateState(goods);
    }

}
