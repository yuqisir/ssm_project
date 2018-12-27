package com.itheima.service.impl;

import com.itheima.domain.Orders;
import com.itheima.mapper.OrdersMapper;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public List<Orders> findAll() throws Exception {
        return ordersMapper.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersMapper.findById(id);
    }
}
