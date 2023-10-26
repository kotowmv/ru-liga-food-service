package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.OrderStatus;
import ru.liga.service.OrderService;
import java.util.List;

@Tag(name = "Kitchen service / API для работы с заказами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Список заказов")
    @GetMapping("/orders")
    public List<OrderDTO> orderList() {
        return orderService.getDtoList();
    }

    @Operation(summary = "Список заказов / MyBatis")
    @GetMapping("/orders2")
    public List<OrderDTO> orderList2() {
        return orderService.getDtoListWithMyBatis();
    }

    @Operation(summary = "Получение заказа по ID")
    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable Integer id) {
        return orderService.getDtoById(id);
    }

    @Operation(summary = "Получение заказа по ID / MyBatis")
    @GetMapping("/order2/{id}")
    public OrderDTO getOrderById2(@PathVariable Integer id) {
        return orderService.getDtoByIdWithMyBatis(id);
    }

    @Operation(summary = "Обновить статус заказа по ID")
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable Integer id, @RequestBody OrderStatus status) {
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Отправить заказ в службу доставки по ID")
    @PostMapping("/order/{id}")
    public void sendOrderToDeliveryById(@PathVariable Integer id) {
        String message = id.toString();
        orderService.sendMessageToCouriers(message);
    }
}
