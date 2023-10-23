package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProducerService producerService;

    public void sendMessageToCouriers (String message) {
        producerService.sendOrder(message, "couriers");
    }
}