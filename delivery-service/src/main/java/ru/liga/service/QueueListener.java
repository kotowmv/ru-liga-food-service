package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.entities.Order;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

    private final OrderService orderService;

    @RabbitListener(queues = "queue1")
    public void processQueue(String message) {
        try {
            Order temp = orderService.getById(Integer.parseInt(message));
            orderService.addToCurrentOrders(temp);
            log.info("Add new order with id = " + message);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid order id = " + message);
        }
    }
}