package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DTO заказа")
@Data
public class OrderDTO {
    @Schema(description = "Идентификатор заказа")
    private int id;

    @Schema(description = "Идентификатор клиента")
    private int customerId;

    @Schema(description = "Идентификатор ресторана")
    private int restaurantId;

    @Schema(description = "Статус заказа")
    private String status;

    @Schema(description = "Идентификатор курьера")
    private int courierId;

    @Schema(description = "Дата и время заказа")
    private String timestamp;
}