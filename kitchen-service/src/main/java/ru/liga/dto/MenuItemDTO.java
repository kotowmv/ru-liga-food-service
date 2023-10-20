package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Schema(description = "DTO позиции меню")
@Data
public class MenuItemDTO {
    @Schema(description = "Идентификатор позиции меню")
    private Integer id;

    @Schema(description = "Идентификатор ресторана")
    private Integer restaurantId;

    @Schema(description = "Наименование позиции")
    private String name;

    @Schema(description = "Стоимость")
    private Double price;

    @Schema(description = "Ссылка на изображение")
    private String image;

    @Schema(description = "Описание")
    private String description;
}
