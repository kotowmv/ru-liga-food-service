package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CourierDTO;
import ru.liga.service.CourierService;
import java.util.List;

@Tag(name = "Delivery service / API для работы с курьерами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @Operation(summary = "Получить список курьеров", description = "Получить список всех существующих курьеров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/couriers")
    public List<CourierDTO> getCourierList() {
        return courierService.getDtoList();
    }

    @Operation(summary = "Получить курьера по ID", description = "Получить одного курьера по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/courier/{id}")
    public CourierDTO getCourierById(@PathVariable Integer id) {
        return courierService.getDtoById(id);
    }

    @Operation(summary = "Добавить курьера", description = "Добавить нового курьера в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/courier")
    public void addCourier(@RequestBody CourierDTO courierDTO) {
        courierService.convertAndSave(courierDTO);
    }

    @Operation(summary = "Удалить курьера по ID", description = "Удалить существующего курьера по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/courier/{id}")
    public void deleteCourierById(@PathVariable Integer id) {
        courierService.deleteById(id);
    }
}
