package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.repositories.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProducerService producerService;

    private final OrderMapper orderMapper;

    public List<OrderDTO> dtoList() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(this::orderToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> dtoList2() {
        return orderMapper.getOrders().stream().map(this::orderToDTO).collect(Collectors.toList());
    }

    public OrderDTO getDtoById(Integer id) {
        return orderToDTO(getById(id));
    }

    public OrderDTO getDtoById2(Integer id) {
        return orderToDTO(getById2(id));
    }

    public Order getById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
    }

    public Order getById2(Integer id) {
        return orderMapper.getOrderById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
    }

    public void updateStatusById(Integer id, OrderStatus status) {
        Optional<Order> row = orderRepository.findById(id);
        if (row.isPresent()) {
            Order order = row.get();
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

    public void sendMessageToCouriers (String message) {
        producerService.sendOrder(message, "couriers");
    }

    public OrderDTO orderToDTO (Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }
}