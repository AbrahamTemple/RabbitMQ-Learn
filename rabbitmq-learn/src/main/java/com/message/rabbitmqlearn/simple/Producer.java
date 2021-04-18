package com.message.rabbitmqlearn.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {

        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //工厂代理的所有连接属性
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;

        try {
            //使用装配好属性的连接工厂进行连接
            connection = connectionFactory.newConnection("生产者");
            //通过连接来获取通道Channel
            channel = connection.createChannel();

//            String exchangeName = "myExchange";
//            String exchangeType = "direct";
//            channel.exchangeDeclare(exchangeName,exchangeType,true);

            /* 声明队列 queueDeclare
            * @param 队列名称
            * @param 是否持久化
            * @param 是否有排他性，独占队列
            * @param 声明自动删除队列，最后一个消费者消费完毕后是否把队列自动删除
            * @param 队列的其它属性
            */
            String queueName = "queue1";
            channel.queueDeclare(queueName,false,false,false,null);
//            channel.queueBind(queueName,exchangeName,"av");
            //准备消息内容
            String message = "各位好儿子们，清明快乐！";
            /*往队列queue里投放消息
             * @param 要将消息发布到的交换机exchange
             * @param 路由密钥，指向一个已声明过的队列
             * @param 支持消息路由头等的其他属性
             * @param body消息内容
             */
            channel.basicPublish("",queueName,null,message.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 关闭通道
            if(channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //关闭连接
            if(connection != null && connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
