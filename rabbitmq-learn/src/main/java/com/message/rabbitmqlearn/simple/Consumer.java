package com.message.rabbitmqlearn.simple;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
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
            /* 接收消息
             * @param 对列名称
             * @param true自动应答：应答成功移除队列中的该消息；false手动应答：先不应答，消息就会重试，直到应带为止
             * @param deliverCallback在传递消息时回调
             * @param cancelCallback当消费者被取消时
             */
            Channel finalChannel = channel;
            finalChannel.basicQos(1);//每一次取多少条消息
            channel.basicConsume("queue1", true,
                new DeliverCallback() {
                    @SneakyThrows
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println("来自生产者发来的消息：" + new String(message.getBody(), "UTF-8"));
                        Thread.sleep(1000);
                        finalChannel.basicAck(message.getEnvelope().getDeliveryTag(),false);//单条消费
                    }
                },
                new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("尝试接收消息失败");
                    }
                 });

            //让程序一直跑下去，否则，queue1队列会因为没有配置持久化而在队列列表中消失
            System.out.println("仍在监听所有的消息...");
            System.in.read();

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
