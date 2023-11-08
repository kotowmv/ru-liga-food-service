package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.entities.RestaurantStatus;

@AllArgsConstructor
@Schema(description = "DTO ресторана")
@Data
public class
RestaurantDTO {
    @Schema(description = "Идентификатор ресторана")
    private Integer id;

    @Schema(description = "Адрес ресторана")
    private String address;

    @Schema(description = "Статус ресторана (открыт/закрыт)")
    private RestaurantStatus status;
}
