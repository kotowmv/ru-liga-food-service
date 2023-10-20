package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CourierDTO;
import ru.liga.entities.Courier;
import ru.liga.repositories.CourierRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Delivery service / API для работы с курьерами")
@RestController
@RequestMapping("/ds")
public class CourierController {
    CourierRepository courierRepository;

    @Autowired
    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Operation(summary = "Список курьеров")
    @GetMapping("/courierList")
    public List<CourierDTO> courierList(){
        List<CourierDTO> list = StreamSupport.stream(courierRepository.findAll().spliterator(), false).map(courier -> courierToDTO(courier)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение курьера по ID")
    @GetMapping("/courier/{courierId}")
    public CourierDTO getCourierById(@PathVariable Integer courierId){
        Optional<Courier> row = courierRepository.findById(courierId);
        if(row.isPresent()) {
            Courier courier = row.get();
            return courierToDTO(courier);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Operation(summary = "Добавить нового курьера")
    @PostMapping("/courier")
    public void addCourier(@RequestBody CourierDTO courierDTO){
        courierRepository.save(courierToEntity(courierDTO));
    }

    @Operation(summary = "Удалить курьера по ID")
    @DeleteMapping ("/courier/{courierId}")
    public void deleteCourier(@PathVariable Integer courierId) {
        courierRepository.deleteById(courierId);
    }

    public CourierDTO courierToDTO (Courier courier) {
        return new CourierDTO(courier.getId(), courier.getPhone(), courier.getStatus(), courier.getCoordinates());
    }

    public Courier courierToEntity (CourierDTO courierDTO) {
        return new Courier(courierDTO.getPhone(), courierDTO.getStatus(), courierDTO.getCoordinates());
    }
}
