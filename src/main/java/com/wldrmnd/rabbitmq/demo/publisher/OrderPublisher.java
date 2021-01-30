package com.wldrmnd.rabbitmq.demo.publisher;

import com.wldrmnd.rabbitmq.demo.config.MessagingConfig;
import com.wldrmnd.rabbitmq.demo.dto.Order;
import com.wldrmnd.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    private final RabbitTemplate template;

    public OrderPublisher(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order,
                            @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurant service
        //payment service
        OrderStatus orderStatus = new OrderStatus(
                order,
                "PROCESS",
                "order placed succesfully in " + restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success";
    }
}