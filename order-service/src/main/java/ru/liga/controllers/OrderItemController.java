package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Получить список позиций заказа", description = "Получить список всех существующих позиций в заказе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/order_items")
    public List<OrderItemDTO> orderItemList() {
        return orderItemService.getDtoList();
    }

    @Operation(summary = "Получить позицию заказа по ID", description = "Получить одну позицию заказа по ее идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/order_item/{id}")
    public OrderItemDTO getOrderItemById(@PathVariable Integer id) {
        return orderItemService.getDtoById(id);
    }

    @Operation(summary = "Добавить позицию заказа", description = "Добавить новую позицию заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order_item")
    public void addOrderItem(@RequestBody OrderItemDTO itemDTO) {
        orderItemService.convertAndSave(itemDTO);
    }

    @Operation(summary = "Удалить позицию заказа по ID", description = "Удалить существующую позицию заказа по ее идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/order_item/{id}")
    public void deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteById(id);
    }
}
