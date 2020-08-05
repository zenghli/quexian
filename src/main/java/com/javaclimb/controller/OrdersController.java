package com.javaclimb.controller;

import com.alibaba.fastjson.JSON;
import com.javaclimb.entity.Orders;
import com.javaclimb.entity.OrdersDetail;
import com.javaclimb.service.OrdersService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单基本信息管理控制层
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "orders/list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Orders orders, Long userId){
        orders.setUid(userId);
        return ordersService.getList(orders);
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "orders/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(String orderInfo,String orderDetails, Long userId){
        if(userId==null){
            return ReturnData.fail("未获取到用户id");
        }
        //解析订单对象
        Orders orders = JSON.parseObject(orderInfo,Orders.class);
        orders.setUid(userId);
        //解析订单详情对象
        List<OrdersDetail> ordersDetails = JSON.parseArray(orderDetails,OrdersDetail.class);
        orders.setOrdersDetailList(ordersDetails);
        return ordersService.add(orders);
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "orders/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Orders orders, Long userId){
        orders.setUid(userId);
        return ordersService.update(orders);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Orders orders){
        return ordersService.updateState(orders);
    }

}
