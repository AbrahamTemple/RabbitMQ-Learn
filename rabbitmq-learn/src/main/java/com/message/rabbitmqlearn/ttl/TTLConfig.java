package com.message.rabbitmqlearn.ttl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Configuration
public class TTLConfig {
    //声明交换机
    @Bean
    public DirectExchange ttlExchange() {
        return new DirectExchange("ttl_exchange", true, false);
    }

    //声明队列
    @Bean
    public Queue ttlQueue() {
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);//设置过期时间5秒
        args.put("x-max-length",6);//最新大消息长度
        args.put("x-dead-letter-exchange","dead_exchange");//绑定死信队列
        args.put("x-dead-letter-routing-key","dead");//死信交换机有路由key一定要配
       return new Queue("ttl.direct.queue", true,false,false,args);
    }

    //绑定交换机与队列
    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("ttl");
    }
}
