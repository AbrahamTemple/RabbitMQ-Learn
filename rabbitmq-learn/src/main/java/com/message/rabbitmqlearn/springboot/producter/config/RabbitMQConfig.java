package com.message.rabbitmqlearn.springboot.producter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //声明交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("order_exchange",true,false);
    }
    //声明短信队列
    @Bean
    public Queue SmsQueue(){
        return new Queue("sms", true);
    }
    //声明邮件队列
    @Bean
    public Queue EmailQueue(){
        return new Queue("email", true);
    }
    //声明支付队列
    @Bean
    public Queue PayQueue(){
        return new Queue("pay", true);
    }
    //绑定交换机与队列
    @Bean
    public Binding SmsBinding(){
        return BindingBuilder.bind(SmsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding EmailBinding(){
        return BindingBuilder.bind(EmailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding PayBinding(){
        return BindingBuilder.bind(PayQueue()).to(fanoutExchange());
    }
}
