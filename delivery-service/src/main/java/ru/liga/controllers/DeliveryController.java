package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;

@Tag(name = "API для взаимодействия с курьерами")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Operation(summary = "Получить заказ по ID")
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable("id") int id) {
        return new OrderDTO();
    }

    @Operation(summary = "Изменить ID курьера")
    @PatchMapping("/{id}/{courier_id}")
    public OrderDTO updateOrderCourierById(@PathVariable(value = "id", required = true) int id,
                                           @PathVariable("courier_id") int courier_id) {
        return new OrderDTO();
    }

    @Operation(summary = "Изменить статус заказа")
    @PatchMapping("/{id}/{status}")
    public OrderDTO updateOrderStatusById(@PathVariable(value = "id", required = true) int id,
                                          @PathVariable("status") String status) {
        return new OrderDTO();
    }

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping("/delete/{id}")
    public String deleteOrderById(@PathVariable(value = "id", required = true) int id) {
        return "Заказ с ID = " + id + " удален";
    }
}
