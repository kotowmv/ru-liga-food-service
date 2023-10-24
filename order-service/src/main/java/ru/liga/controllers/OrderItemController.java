package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderItemDTO;
import ru.liga.service.OrderItemService;
import java.util.List;

@Tag(name = "Order service / API для работы с позициями заказа")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "Список позиций в заказе")
    @GetMapping("/order_items")
    public List<OrderItemDTO> orderItemList(){
        return orderItemService.dtoList();
    }

    @Operation(summary = "Получение позиции заказа по ID")
    @GetMapping("/order_item/{id}")
    public OrderItemDTO getOrderItemById(@PathVariable Integer id){
        return orderItemService.getById(id);
    }

    @Operation(summary = "Добавить позицию в заказ")
    @PostMapping("/order_item")
    public void addOrderItem(@RequestBody OrderItemDTO itemDTO){
        orderItemService.convertAndSave(itemDTO);
    }

    @Operation(summary = "Удалить позицию в заказе по ID")
    @DeleteMapping ("/order_item/{id}")
    public void deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteById(id);
    }
}
