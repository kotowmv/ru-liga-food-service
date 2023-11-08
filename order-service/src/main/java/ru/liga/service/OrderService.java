package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.repositories.OrderRepository;
import java.time.LocalDateTime;
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

    public void convertAndSave(OrderDTO orderDTO) {
        orderRepository.save(orderToEntity(orderDTO));
    }

    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

    public void updateStatusById(UUID id, OrderStatus status) {
        orderRepository.updateOrderStatusById(id, String.valueOf(status));
    }

    public void payById(UUID id) {
        Order order = getById(id);
        if (order.getStatus() == OrderStatus.CUSTOMER_CREATED) {
            updateStatusById(id, OrderStatus.CUSTOMER_PAID);
            log.info("Order with id = " + id + " successfully paid");
        } else {
            log.info("Can't pay order. Order with id = " + id + " has status " + order.getStatus());
        }
    }

    public void cancelById(UUID id) {
        Order order = getById(id);
        if (order.getStatus() == OrderStatus.CUSTOMER_CREATED || order.getStatus() == OrderStatus.CUSTOMER_PAID) {
            updateStatusById(id, OrderStatus.CUSTOMER_CANCELLED);
            log.info("Order with id = " + id + " successfully cancelled");
        } else {
            log.info("Can't cancel order. Order with id = " + id + " has status " + order.getStatus());
        }
    }

    public void sendNewOrderToKitchen(String message) {
        Order readyOrder = getById(UUID.fromString(message));
        if (readyOrder.getStatus() == OrderStatus.CUSTOMER_PAID) {
            producerService.sendMessage("order; " + message, "kitchen");
            log.info("Order with id = " + message + " send to kitchen");
        } else if (readyOrder.getStatus() == OrderStatus.CUSTOMER_CREATED) {
            log.info("Can't send to kitchen. Order with id = " + message + " not paid");
        } else {
            log.info("Can't send to kitchen. Order with id = " + message + " has status " + readyOrder.getStatus());
        }
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }

    public Order orderToEntity(OrderDTO orderDTO) {
        Order newOrder = new Order(orderDTO.getCustomerId(), orderDTO.getRestaurantId(), orderDTO.getStatus(), orderDTO.getCourierId(), orderDTO.getTimestamp());
        if (newOrder.getId() == null) {
            newOrder.setId(UUID.randomUUID());
        }
        if (newOrder.getStatus() == null) {
            newOrder.setStatus(OrderStatus.CUSTOMER_CREATED);
        }
        if (newOrder.getTimestamp() == null) {
            newOrder.setTimestamp(LocalDateTime.now());
        }
        return newOrder;
    }
}
