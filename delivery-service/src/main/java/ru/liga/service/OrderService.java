package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.feignclient.KitchenServiceFeignClient;
import ru.liga.repositories.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final KitchenServiceFeignClient feignClient;

    public List<OrderDTO> dtoList() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(this::orderToDTO).collect(Collectors.toList());
    }

    public OrderDTO getById(Integer id) {
        return orderRepository.findById(id)
                .map(this::orderToDTO)
                .orElseThrow(() -> new RuntimeException("Order with id = " + id + "is not exists"));
    }

    public void updateStatusById(Integer id, OrderStatus status) {
        feignClient.updateOrderStatusById(id, status);
    }

    public void acceptById(Integer id) {
        feignClient.updateOrderStatusById(id, OrderStatus.DELIVERY_PICKING);
    }

    public void rejectById(Integer id) {
        feignClient.updateOrderStatusById(id, OrderStatus.DELIVERY_DENIED);
    }

    public OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }
}
