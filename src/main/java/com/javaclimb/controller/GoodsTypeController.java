package com.javaclimb.controller;

import com.javaclimb.entity.GoodsType;
import com.javaclimb.service.GoodsTypeService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分类控制层
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "goodsType/list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(GoodsType goodsType){
        return goodsTypeService.getList(goodsType);
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "goodsType/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(GoodsType goodsType){
        return goodsTypeService.add(goodsType);
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "goodsType/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(GoodsType goodsType){
        return goodsTypeService.update(goodsType);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(GoodsType goodsType){
        return goodsTypeService.updateState(goodsType);
    }


}
