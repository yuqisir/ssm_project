package com.itheima.controller;

import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        List<Orders> ordersList = ordersService.findAll();
        model.addAttribute("ordersList",ordersList);
        return "orders-list";
    }
    //根据ID查询订单详情
    @RequestMapping("/findById")
    public String findOrdersById(String id,Model model) throws Exception {
        Orders orders=ordersService.findById(id);
        model.addAttribute("orders",orders);
        return "orders-show";
    }
}
