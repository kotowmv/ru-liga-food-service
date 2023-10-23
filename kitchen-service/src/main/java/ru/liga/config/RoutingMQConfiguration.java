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
        Queue queueDirectFirst = new Queue("queue1", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("couriers"));
    }
}