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

@Tag(name = "Kitchen service / API для работы с заказами")
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
    public List<OrderDTO> orderList() {
        return orderService.getDtoList();
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
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable UUID id, @RequestBody OrderStatus status) {
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Принять заказ по ID", description = "Установить статус существующего оплаченного заказа в \"KITCHEN_ACCEPTED\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/accept/{id}")
    public void acceptOrderById(@PathVariable UUID id) {
        orderService.acceptById(id);
    }

    @Operation(summary = "Отклонить заказ по ID", description = "Установить статус существующего оплаченного заказа в \"KITCHEN_DENIED\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/reject/{id}")
    public void rejectOrderById(@PathVariable UUID id) {
        orderService.rejectById(id);
    }

    @Operation(summary = "Сообщить о готовности заказа по ID", description = "Установить статус существующего заказа в \"KITCHEN_PREPARED\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/pay/{id}")
    public void readyOrderById(@PathVariable UUID id) {
        orderService.readyById(id);
    }

    @Operation(summary = "Отправить заказ в службу доставки по ID", description = "Отправить сообщение с идентификатором нового готового заказа в доставку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/{id}")
    public void sendOrderToDeliveryById(@PathVariable UUID id) {
        String message = id.toString();
        orderService.sendNewOrderToDelivery(message);
    }
}
