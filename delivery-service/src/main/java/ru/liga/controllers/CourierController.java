package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Список курьеров")
    @GetMapping("/couriers")
    public List<CourierDTO> courierList(){
        return courierService.dtoList();
    }

    @Operation(summary = "Получение курьера по ID")
    @GetMapping("/courier/{id}")
    public CourierDTO getCourierById(@PathVariable Integer id){
        return courierService.getDtoById(id);
    }

    @Operation(summary = "Добавить нового курьера")
    @PostMapping("/courier")
    public void addCourier(@RequestBody CourierDTO courierDTO){
        courierService.convertAndSave(courierDTO);
    }

    @Operation(summary = "Удалить курьера по ID")
    @DeleteMapping ("/courier/{id}")
    public void deleteCourierById(@PathVariable Integer id) {
        courierService.deleteById(id);
    }
}
