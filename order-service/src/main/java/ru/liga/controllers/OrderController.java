package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;

@Tag(name = "API для работы с заказами")
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
    public String updateOrderById(@RequestBody OrderDTO dto, @PathVariable(value = "id", required = true) int id) {
        return "Заказ изменен";
    }

    @Operation(summary = "Изменить статус заказа")
    @PatchMapping("/{id}/{status}")
    public String updateOrderStatusById(@PathVariable(value = "id", required = true) int id,
                                        @PathVariable("status") String status) {
        return "Статус заказа изменен";
    }

    @Operation(summary = "Изменить ID курьера")
    @PatchMapping("/{id}/{courier_id}")
    public String updateOrderCourierById(@PathVariable(value = "id", required = true) int id,
                                         @PathVariable("courier_id") int courier_id) {
        return "ID курьера изменен";
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping("/create")
    public String createOrder(@RequestBody OrderDTO orderDto) {
        return "Новый заказ создан";
    }

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping("/delete/{id}")
    public String deleteOrderById(@PathVariable(value = "id", required = true) int id) {
        return "Заказ с ID = " + id + " удален";
    }
}
