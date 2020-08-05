package com.javaclimb.controller;

import com.javaclimb.entity.OrdersDetail;
import com.javaclimb.service.OrdersDetailService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单详情管理控制层
 */
@Controller
@RequestMapping("/ordersDetail")
public class OrdersDetailController {

    @Autowired
    private OrdersDetailService ordersDetailService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "ordersDetail/list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(OrdersDetail ordersDetail){
        return ordersDetailService.getList(ordersDetail);
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "ordersDetail/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(OrdersDetail ordersDetail){
        return ordersDetailService.add(ordersDetail);
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "ordersDetail/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(OrdersDetail ordersDetail, Integer userId){
        return ordersDetailService.update(ordersDetail);
    }

}
