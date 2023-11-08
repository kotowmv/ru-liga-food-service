package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.entities.OrderStatus;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Schema(description = "DTO заказа")
@Data
public class OrderDTO {
    @Schema(description = "Идентификатор заказа")
    private UUID id;

    @Schema(description = "Идентификатор клиента")
    private Integer customerId;

    @Schema(description = "Идентификатор ресторана")
    private Integer restaurantId;

    @Schema(description = "Статус заказа")
    private OrderStatus status;

    @Schema(description = "Идентификатор курьера")
    private Integer courierId;

    @Schema(description = "Дата и время заказа")
    private LocalDateTime timestamp;
}