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

    @Operation(summary = "Получить заказ по ID", description = "Получить один заказ по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(@PathVariable Integer id) {
        return orderService.getDtoById(id);
    }

    @Operation(summary = "Обновить статус заказа по ID", description = "Обновить статус существующего заказа по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable Integer id, OrderStatus status) {
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Принять заказ по ID", description = "Установить статус существующего заказа в \"DELIVERY_PICKING\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/accept/{id}")
    public void acceptOrderById(@PathVariable Integer id) {
        orderService.acceptById(id);
    }

    @Operation(summary = "Отклонить заказ по ID", description = "Установить статус существующего заказа в \"DELIVERY_DENIED\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/reject/{id}")
    public void rejectOrderById(@PathVariable Integer id) {
        orderService.rejectById(id);
    }

    @Operation(summary = "Список текущих заказов", description = "Получить список всех существующих заказов со статусами \"DELIVERY_PENDING\", \"DELIVERY_PICKING\", \"DELIVERY_DELIVERING\"")
    @GetMapping("/current_orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    public List<OrderDTO> getCurrentOrderList() {
        return orderService.getAllCurrentOrdersDtoList();
    }

    @Operation(summary = "Получить количество текущих заказов", description = "Получить количество всех существующих заказов со статусами \"DELIVERY_PENDING\", \"DELIVERY_PICKING\", \"DELIVERY_DELIVERING\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/current_orders/count")
    public Integer getCurrentOrderCount() {
        return orderService.getCountOfCurrentOrders();
    }
}
