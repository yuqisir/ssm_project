package com.itheima.mapper;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersMapper {
    //遍历订单
    List<Orders> findAll() throws Exception;

}
