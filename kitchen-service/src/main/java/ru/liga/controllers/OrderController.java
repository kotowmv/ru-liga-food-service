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

    @Operation(summary = "Получить список заказов / MyBatis", description = "Получить список всех существующих заказов (для проверки работоспособности MyBatis)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/orders2")
    public List<OrderDTO> orderList2() {
        return orderService.getDtoListWithMyBatis();
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

    @Operation(summary = "Получить заказ по ID / MyBatis", description = "Получить один заказ по его идентификатору (для проверки работоспособности MyBatis)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/order2/{id}")
    public OrderDTO getOrderById2(@PathVariable Integer id) {
        return orderService.getDtoByIdWithMyBatis(id);
    }

    @Operation(summary = "Обновить статус заказа по ID", description = "Обновить статус существующего заказа по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PatchMapping("/order/{id}")
    public void updateOrderStatusById(@PathVariable Integer id, @RequestBody OrderStatus status) {
        orderService.updateStatusById(id, status);
    }

    @Operation(summary = "Отправить заказ в службу доставки по ID", description = "Отправить сообщение с номером заказа в службу доставки по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/order/{id}")
    public void sendOrderToDeliveryById(@PathVariable Integer id) {
        String message = id.toString();
        orderService.sendMessageToCouriers(message);
    }
}
