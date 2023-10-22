package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import ru.liga.feignclient.KitchenServiceFeignClient;
import ru.liga.repositories.OrderRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Delivery service / API для работы с заказами")
@RestController
@RequestMapping("/ds")
public class OrderController {
    OrderRepository orderRepository;
    KitchenServiceFeignClient feignClient;

    @Autowired
    public OrderController(OrderRepository orderRepository, KitchenServiceFeignClient feignClient) {
        this.orderRepository = orderRepository;
        this.feignClient = feignClient;
    }

    @Operation(summary = "Список заказов")
    @GetMapping("/orderList")
    public List<OrderDTO> orderList(){
        List<OrderDTO> list = StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(order -> orderToDTO(order)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение заказа по ID")
    @GetMapping("/order/{orderId}")
    public OrderDTO getOrderById(@PathVariable Integer orderId){
        Optional<Order> row = orderRepository.findById(orderId);
        if(row.isPresent()) {
            Order order = row.get();
            return orderToDTO(order);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Operation(summary = "Обновить статус заказа по ID")
    @PatchMapping("/order/{orderId}")
    public void updateOrderStatusById(@PathVariable Integer orderId, OrderStatus status){
        feignClient.updateOrderStatusById(orderId, status);
    }

    @Operation(summary = "Принять заказ по ID")
    @PostMapping("/order/accept/{orderId}")
    public void acceptOrderById(@PathVariable Integer orderId){
        feignClient.updateOrderStatusById(orderId, OrderStatus.DELIVERY_PICKING);
    }

    @Operation(summary = "Отклонить заказ по ID")
    @PostMapping("/order/reject/{orderId}")
    public void rejectOrderById(@PathVariable Integer orderId){
        feignClient.updateOrderStatusById(orderId, OrderStatus.DELIVERY_DENIED);
    }

    public OrderDTO orderToDTO (Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getRestaurantId(), order.getStatus(), order.getCourierId(), order.getTimestamp());
    }
}
