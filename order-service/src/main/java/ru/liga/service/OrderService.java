package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
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

    public List<OrderDTO> getDtoList() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(this::orderToDTO).collect(Collectors.toList());
    }

    public OrderDTO getDtoById(Integer id) {
        return orderToDTO(getById(id));
    }

    public Order getById(Integer id) {
        if (id > 0) {
            return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id = " + id + " is not exists"));
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public void convertAndSave(OrderDTO orderDTO) {
        orderRepository.save(orderToEntity(orderDTO));
    }

    public void deleteById(Integer id) {
        if (id > 0) {
            try {
                orderRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new RuntimeException("Order with id = " + id + " is not exists");
            }
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public void updateStatusById(Integer id, OrderStatus status) {
        if (id > 0) {
            Optional<Order> row = orderRepository.findById(id);
            if (row.isPresent()) {
                Order order = row.get();
                order.setStatus(status);
                orderRepository.save(order);
            }
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }

    public Order orderToEntity(OrderDTO orderDTO) {
        return new Order(orderDTO.getCustomerId(), orderDTO.getRestaurantId(), orderDTO.getStatus(), orderDTO.getCourierId(), orderDTO.getTimestamp());
    }
}
