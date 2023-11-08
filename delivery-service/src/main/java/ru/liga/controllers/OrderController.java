package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entities.OrderStatus;
import ru.liga.service.OrderService;
import java.util.List;
import java.util.UUID;

@Tag(name = "Delivery service / API для работы с заказами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Получить список заказов", description = "Получить список всех существующих заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/orders")
    public List<OrderDTO> getOrderList() {
        return orderService.getDtoList();
    }

    @Operation(summary = "Получить список активных заказов", description = "Получить список всех существующих заказов со статусом \"KITCHEN_PREPARED\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/orders/active")
    public List<OrderDTO> getActiveOrderList() {
        return orderService.getActiveDtoList();
    }

    @Operation(summary = "Получить заказ по ID", description = "Получить один заказ по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable UUID id) {
        return orderService.getDtoById(id);
    }

    @Operation(summary = "Обновить статус заказа по ID", description = "Обновить статус существующего заказа по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PatchMapping("/order/status/{id}")
    public void updateOrderStatusById(@PathVariable UUID id, @RequestBody OrderStatus status) {
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Назначить курьера для заказа по ID", description = "Назначить курьера для существующего заказа по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PatchMapping("/order/courier/{id}")
    public void setCourierToOrderById(@PathVariable UUID id, @RequestBody Integer courierId) {
        orderService.setCourierToOrder(id, courierId);
    }

    @Operation(summary = "Принять заказ по ID", description = "Установить статус существующего заказа в \"DELIVERY_PICKING\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/accept/{id}")
    public void acceptOrderById(@PathVariable UUID id) {
        orderService.acceptById(id);
    }

    @Operation(summary = "Отклонить заказ по ID", description = "Установить статус существующего заказа в \"DELIVERY_DENIED\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/reject/{id}")
    public void rejectOrderById(@PathVariable UUID id) {
        orderService.rejectById(id);
    }

    @Operation(summary = "Сообщить о получении заказа курьером из ресторана по ID", description = "Установить статус существующего заказа в \"DELIVERY_DELIVERING\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/receive/{id}")
    public void receiveOrderById(@PathVariable UUID id) {
        orderService.receiveById(id);
    }

    @Operation(summary = "Завершить доставку заказа по ID", description = "Установить статус существующего заказа в \"DELIVERY_COMPLETE\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/complete/{id}")
    public void completeOrderById(@PathVariable UUID id) {
        orderService.completeById(id);
    }
}
