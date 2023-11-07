package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantDTO;
import ru.liga.service.RestaurantService;
import java.util.List;

@Tag(name = "Kitchen service / API для работы с ресторанами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Получить список ресторанов", description = "Получить список всех существующих ресторанов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/restaurants")
    public List<RestaurantDTO> getRestaurantList() {
        return restaurantService.getDtoList();
    }

    @Operation(summary = "Получить ресторан по ID", description = "Получить один ресторан по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/restaurant/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Integer id) {
        return restaurantService.getDtoById(id);
    }

    @Operation(summary = "Добавить ресторан", description = "Добавить новый ресторан в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/restaurant")
    public void addOrderItem(@RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.convertAndSave(restaurantDTO);
    }

    @Operation(summary = "Удалить ресторан по ID", description = "Удалить существующий ресторан по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/restaurant/{id}")
    public void deleteOrderItem(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }
}
