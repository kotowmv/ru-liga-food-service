package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.entities.OrderStatus;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

    @RabbitListener(queues = "orderQueue")
    public void processQueue(String message) {
        String[] values = message.split(";",2);
        UUID id = UUID.fromString(values[0].trim());
        OrderStatus status = OrderStatus.valueOf(values[1].trim());
        log.info("Order with id = " + id + " change status to " + status);
    }
}