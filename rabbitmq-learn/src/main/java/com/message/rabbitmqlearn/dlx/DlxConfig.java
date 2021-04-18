package com.message.rabbitmqlearn.dlx;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DlxConfig {
    //声明死信交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_exchange", true, false);
    }

    //声明死信队列
    @Bean
    public Queue deadQueue() {
        return new Queue("dead.direct.queue", true);
    }

    //绑定交换机与队列,配置路由key
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }
}
