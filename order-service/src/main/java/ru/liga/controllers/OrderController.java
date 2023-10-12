package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;

@Tag(name = "API для создания заказов")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Operation(summary = "Получить заказ по ID")
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable("id") int id) {
        return new OrderDTO();
    }

    @Operation(summary = "Обновить все данные заказа по ID")
    @PutMapping("/update/{id}")
    public OrderDTO updateOrderById(@RequestBody OrderDTO dto, @PathVariable(value = "id", required = true) int id) {
        return new OrderDTO();
    }

    @Operation(summary = "Изменить статус заказа")
    @PatchMapping("/{id}/{status}")
    public OrderDTO updateOrderStatusById(@PathVariable(value = "id", required = true) int id,
                                        @PathVariable("status") String status) {
        return new OrderDTO();
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDto) {
        return new OrderDTO();
    }

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping("/delete/{id}")
    public String deleteOrderById(@PathVariable(value = "id", required = true) int id) {
        return "Заказ с ID = " + id + " удален";
    }
}
