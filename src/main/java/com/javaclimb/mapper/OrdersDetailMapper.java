package com.javaclimb.mapper;

import com.javaclimb.entity.OrdersDetail;
import com.javaclimb.entity.OrdersDetail;

import java.util.List;

/**
 * 订单详情管理
 */
public interface OrdersDetailMapper {
    List<OrdersDetail> getList(OrdersDetail ordersDetail);
    int add(OrdersDetail ordersDetail);
    int update(OrdersDetail ordersDetail);
    //根据订单id查询订单详情
    List<OrdersDetail> getListByOid(Integer oid);
}
