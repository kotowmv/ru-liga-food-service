package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Schema(description = "DTO клиента")
@Data
public class CustomerDTO {
    @Schema(description = "Идентификатор клиента")
    private Integer id;

    @Schema(description = "Номер телефона")
    private String phone;

    @Schema(description = "Электронная почта")
    private String email;

    @Schema(description = "Адрес")
    private String address;
}
