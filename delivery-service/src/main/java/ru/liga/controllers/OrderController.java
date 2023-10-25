package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.OrderStatus;
import ru.liga.service.OrderService;
import java.util.List;

@Tag(name = "Delivery service / API для работы с заказами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Список заказов")
    @GetMapping("/orders")
    public List<OrderDTO> orderList(){
        return orderService.dtoList();
    }

    @Operation(summary = "Получение заказа по ID")
    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable Integer id){
        return orderService.getDtoById(id);
    }

    @Operation(summary = "Обновить статус заказа по ID")
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable Integer id, OrderStatus status){
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Принять заказ по ID")
    @PostMapping("/order/accept/{id}")
    public void acceptOrderById(@PathVariable Integer id){
        orderService.acceptById(id);
    }

    @Operation(summary = "Отклонить заказ по ID")
    @PostMapping("/order/reject/{id}")
    public void rejectOrderById(@PathVariable Integer id){
        orderService.rejectById(id);
    }

    @Operation(summary = "Список текущих заказов")
    @GetMapping("/current_orders")
    public List<OrderDTO> currentOrderList(){
        return orderService.getAllCurrentOrdersDtoList();
    }

    @Operation(summary = "Количество текущих заказов")
    @GetMapping("/current_orders/count")
    public Integer currentOrderCount(){
        return orderService.getCountOfCurrentOrders();
    }
}
