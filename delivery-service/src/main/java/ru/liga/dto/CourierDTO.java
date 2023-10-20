package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.entities.CourierStatus;

@AllArgsConstructor
@Schema(description = "DTO курьера")
@Data
public class CourierDTO {
    @Schema(description = "Идентификатор курьера")
    private Integer id;

    @Schema(description = "Номер телефона")
    private String phone;

    @Schema(description = "Статус")
    private CourierStatus status;

    @Schema(description = "Координаты")
    private String coordinates;
}