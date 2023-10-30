package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Список ресторанов")
    @GetMapping("/restaurants")
    public List<RestaurantDTO> getRestaurantList() {
        return restaurantService.getDtoList();
    }

    @Operation(summary = "Получение ресторана по ID")
    @GetMapping("/restaurant/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Integer id) {
        return restaurantService.getDtoById(id);
    }

    @Operation(summary = "Добавить ресторан")
    @PostMapping("/restaurant")
    public void addOrderItem(@RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.convertAndSave(restaurantDTO);
    }

    @Operation(summary = "Удалить ресторан по ID")
    @DeleteMapping("/restaurant/{id}")
    public void deleteOrderItem(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/test")
    public String message() {
        return "Kitchen service is working successfully";
    }
}
