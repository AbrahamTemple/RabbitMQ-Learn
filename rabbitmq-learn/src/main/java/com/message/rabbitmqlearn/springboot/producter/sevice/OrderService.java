package com.message.rabbitmqlearn.springboot.producter.sevice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class OrderService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    //下单
    public String MakeOrder(String user,String productId,int count){
        String orderId = UUID.randomUUID().toString();
        /* 消息队列分发订单
         * @param 交换机
         * @param 路由key/队列名
         * @param 消息内容
         */
        String exchangeName = "order_exchange";
        String routerKey = "";

        rabbitTemplate.convertAndSend(exchangeName,routerKey,orderId);
        return orderId;
    }
}
