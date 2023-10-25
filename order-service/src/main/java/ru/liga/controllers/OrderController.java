package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.OrderStatus;
import ru.liga.service.OrderService;
import java.util.List;

@Tag(name = "Order service / API для работы с заказами")
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

    @Operation(summary = "Добавить новый заказ")
    @PostMapping("/order")
    public void addOrder(@RequestBody OrderDTO orderDTO){
        orderService.convertAndSave(orderDTO);
    }

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping ("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteById(id);
    }

    @Operation(summary = "Обновить статус заказа по ID")
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable Integer id, @RequestBody OrderStatus status) {
        orderService.updateStatusById(id, status);
    }
}
