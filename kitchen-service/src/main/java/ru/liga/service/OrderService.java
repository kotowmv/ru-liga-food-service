package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.repositories.OrderRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProducerService producerService;

    public List<OrderDTO> getDtoList() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(this::orderToDTO).collect(Collectors.toList());
    }

    public OrderDTO getDtoById(UUID id) {
        return orderToDTO(getById(id));
    }

    public Order getById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
    }

    public void updateStatusById(UUID id, OrderStatus status) {
        sendNewStatusToOrder(id, status);
        orderRepository.updateOrderStatusById(id, String.valueOf(status));
    }

    public void acceptById(UUID id) {
        Order readyOrder = getById(id);
        if (readyOrder.getStatus() == OrderStatus.CUSTOMER_PAID) {
            updateStatusById(id, OrderStatus.KITCHEN_ACCEPTED);
        } else {
            log.info("Can't accept by kitchen. Order with id = " + id + " has status " + readyOrder.getStatus());
        }
    }

    public void rejectById(UUID id) {
        Order readyOrder = getById(id);
        if (readyOrder.getStatus() == OrderStatus.CUSTOMER_PAID) {
            updateStatusById(id, OrderStatus.KITCHEN_DENIED);
        } else {
            log.info("Can't reject by kitchen. Order with id = " + id + " has status " + readyOrder.getStatus());
        }
    }

    public void readyById(UUID id) {
        Order readyOrder = getById(id);
        if (readyOrder.getStatus() == OrderStatus.KITCHEN_ACCEPTED) {
            updateStatusById(id, OrderStatus.KITCHEN_PREPARED);
        } else {
            log.info("Can't ready by kitchen. Order with id = " + id + " has status " + readyOrder.getStatus());
        }
    }

    public void sendNewStatusToOrder(UUID id, OrderStatus status) {
        String message = id + "; " + status;
        producerService.sendMessage(message, "order");
        log.info("Order with id = " + id + " change status to " + status);
    }

    public void sendNewOrderToDelivery(String message) {
        Order readyOrder = getById(UUID.fromString(message));
        if (readyOrder.getStatus() == OrderStatus.KITCHEN_PREPARED) {
            producerService.sendMessage(message, "delivery");
            log.info("Order with id = " + message + " send to delivery");
        } else {
            log.info("Can't send to kitchen. Order with id = " + message + " has status " + readyOrder.getStatus());
        }
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }
}