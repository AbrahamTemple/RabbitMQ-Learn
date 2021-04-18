package com.message.rabbitmqlearn.ttl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class TtlController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/ttl")
    public String ttlRequest(){
        String orderId = UUID.randomUUID().toString();

        String exchangeName = "ttl_exchange";
        String routerKey = "ttl";
        //取出每条消息进一步进行设置
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");//设置5秒过期
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };

        rabbitTemplate.convertAndSend(exchangeName,routerKey,orderId);
        return orderId;
    }
}
