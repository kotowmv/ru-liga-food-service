package ru.liga.service;

public interface RabbitMQProducerService {
    void sendMessage(String message, String routingKey);
}