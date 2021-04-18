package com.message.rabbitmqlearn.springboot.cusumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"email"})
@Component
public class EmailConsumer {
    @RabbitHandler
    public void receiveMessage(String msg){
        System.out.println("您的邮箱已经打印了订单收据"+msg);
        //return "您的邮箱已经打印了订单收据"+msg;
    }
}
