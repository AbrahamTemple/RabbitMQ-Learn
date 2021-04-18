package com.message.rabbitmqlearn.springboot.cusumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"pay"})
@Component
public class PayConsumer {
    @RabbitHandler
    public void receiveMessage(String msg){
        System.out.println("您的订单"+msg+"已经完成了预付款");
        //return "您的订单"+msg+"已经完成了预付款";
    }
}
