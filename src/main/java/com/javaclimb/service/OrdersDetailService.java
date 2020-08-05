package com.javaclimb.service;

import com.javaclimb.entity.OrdersDetail;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;

/**
 * 订单详情service接口
 */
public interface OrdersDetailService {
    ReturnDataForLayui getList(OrdersDetail ordersDetail);
    ReturnData add(OrdersDetail ordersDetail);
    ReturnData update(OrdersDetail ordersDetail);
}
