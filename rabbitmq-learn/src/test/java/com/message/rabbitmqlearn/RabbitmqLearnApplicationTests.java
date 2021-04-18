package com.message.rabbitmqlearn;

import com.message.rabbitmqlearn.springboot.producter.sevice.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitmqLearnApplicationTests {

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.MakeOrder("Lueue","16",1);
    }

}
