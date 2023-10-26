package ru.liga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService implements RabbitMQProducerService {

    private final RabbitTemplate template;

    @Autowired
    public ProducerService(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void sendOrder(String message, String routingKey) {
        template.convertAndSend("directExchange", routingKey, message);
        log.info("Message has been sanded");
    }
}