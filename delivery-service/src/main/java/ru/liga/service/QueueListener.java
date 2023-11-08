package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

    @RabbitListener(queues = "deliveryQueue")
    public void processQueue(String message) {
        log.info("Add new order to delivery with id = " + message);
    }
}