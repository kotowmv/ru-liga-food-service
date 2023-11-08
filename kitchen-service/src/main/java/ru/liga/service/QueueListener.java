package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

    @RabbitListener(queues = "kitchenQueue")
    public void processQueue(String message) {
        String[] values = message.split(";",2);
        String producer = values[0].trim();
        if (producer.equals("order")) {
            UUID order = UUID.fromString(values[1].trim());
            log.info("Add new order to kitchen with id = " + order);
        } else if (producer.equals("delivery")) {
            UUID order = UUID.fromString(values[1].trim());
            log.info("Order with id = " + order + " was accepted by delivery");
        }
    }
}