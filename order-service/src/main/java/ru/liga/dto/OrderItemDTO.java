package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@AllArgsConstructor
@Schema(description = "DTO позиции заказа")
@Data
public class OrderItemDTO {
    @Schema(description = "Идентификатор позиции заказа")
    private Integer id;

    @Schema(description = "Идентификатор заказа")
    private UUID orderId;

    @Schema(description = "Идентификатор позиции меню")
    private Integer menuItemId;

    @Schema(description = "Стоимость")
    private Double price;

    @Schema(description = "Количество")
    private Integer quantity;
}