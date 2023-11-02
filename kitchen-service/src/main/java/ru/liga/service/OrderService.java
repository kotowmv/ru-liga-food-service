package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.repositories.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProducerService producerService;

    private final OrderMapper orderMapper;

    public List<OrderDTO> getDtoList() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(this::orderToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getDtoListWithMyBatis() {
        return orderMapper.getOrders().stream().map(this::orderToDTO).collect(Collectors.toList());
    }

    public OrderDTO getDtoById(Integer id) {
        return orderToDTO(getById(id));
    }

    public OrderDTO getDtoByIdWithMyBatis(Integer id) {
        return orderToDTO(getByIdWithMyBatis(id));
    }

    public Order getById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
    }

    public Order getByIdWithMyBatis(Integer id) {
        return orderMapper.getOrderById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
    }

    public void updateStatusById(Integer id, OrderStatus status) {
        orderRepository.updateOrderStatusById(id, status);
    }

    public void sendMessageToCouriers(String message) {
        producerService.sendOrder(message, "couriers");
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }
}