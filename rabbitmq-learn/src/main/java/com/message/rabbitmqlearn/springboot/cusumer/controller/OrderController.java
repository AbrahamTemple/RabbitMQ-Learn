package com.message.rabbitmqlearn.springboot.cusumer.controller;

import com.message.rabbitmqlearn.springboot.cusumer.service.EmailConsumer;
import com.message.rabbitmqlearn.springboot.cusumer.service.PayConsumer;
import com.message.rabbitmqlearn.springboot.cusumer.service.SmsConsumer;
import com.message.rabbitmqlearn.springboot.producter.sevice.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/send/{user}/{pid}/{count}")
    public String send
            (@PathVariable(value = "user") String user,
             @PathVariable(value = "pid") String pid,
             @PathVariable(value = "count") Integer count){
        return orderService.MakeOrder(user,pid,count);
    }
}
