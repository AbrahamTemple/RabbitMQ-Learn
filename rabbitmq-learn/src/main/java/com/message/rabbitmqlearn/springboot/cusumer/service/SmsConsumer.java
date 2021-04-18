package com.message.rabbitmqlearn.springboot.cusumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms"})
@Component
public class SmsConsumer {
    @RabbitHandler
    public void receiveMessage(String msg){
        System.out.println("您收到了一条短信来自于您的交易订单"+msg);
        //return "您收到了一条短信来自于您的交易订单"+msg;
    }
}
