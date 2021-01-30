package com.wldrmnd.rabbitmq.demo.consumer;

import com.wldrmnd.rabbitmq.demo.config.MessagingConfig;
import com.wldrmnd.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue: " + orderStatus);
    }
}