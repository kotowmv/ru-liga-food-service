package ru.liga.service;

public interface RabbitMQProducerService {
    void sendOrder(String message, String routingKey);
}