package ru.liga.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMQConfiguration {
    @Bean
    public Declarables myQueue() {
        Queue queueDirectDelivery = new Queue("deliveryQueue", false);
        Queue queueDirectKitchen = new Queue("kitchenQueue", false);
        Queue queueDirectOrder = new Queue("orderQueue", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectDelivery, queueDirectKitchen, queueDirectOrder, directExchange,
                BindingBuilder.bind(queueDirectDelivery).to(directExchange).with("delivery"),
                BindingBuilder.bind(queueDirectKitchen).to(directExchange).with("kitchen"),
                BindingBuilder.bind(queueDirectOrder).to(directExchange).with("order"));
    }
}