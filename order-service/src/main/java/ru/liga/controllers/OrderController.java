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

@Tag(name = "Order service / API для работы с заказами")
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

    @Operation(summary = "Добавить заказ", description = "Добавить новый заказ в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order")
    public void addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.convertAndSave(orderDTO);
    }

    @Operation(summary = "Оплатить заказ по ID", description = "Установить статус существующего заказа в \"CUSTOMER_PAID\" по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/pay/{id}")
    public void payOrderById(@PathVariable UUID id) {
        orderService.payById(id);
    }

    @Operation(summary = "Отменить заказ по ID", description = "Установить статус существующего заказа в \"CUSTOMER_CANCELLED\" по идентификатору. Доступно только для заказов со статусами \"CUSTOMER_CREATED\" и \"CUSTOMER_PAID\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/cancel/{id}")
    public void cancelOrderById(@PathVariable UUID id) {
        orderService.cancelById(id);
    }

    @Operation(summary = "Отправить заказ в ресторан по ID", description = "Отправить сообщение с идентификатором нового оплаченного заказа в ресторан")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/{id}")
    public void sendNewOrderToKitchenById(@PathVariable UUID id) {
        String message = id.toString();
        orderService.sendNewOrderToKitchen(message);
    }

    @Operation(summary = "Удалить заказ по ID", description = "Удалить существующий заказ по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteById(id);
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
}
