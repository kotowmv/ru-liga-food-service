package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entities.Restaurant;
import ru.liga.repositories.RestaurantRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Delivery service / API для работы с ресторанами")
@RestController
@RequestMapping("/ds")
public class RestaurantController {
    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Operation(summary = "Список ресторанов")
    @GetMapping("/restaurantList")
    public List<RestaurantDTO> getRestaurantList(){
        List<RestaurantDTO> list = StreamSupport.stream(restaurantRepository.findAll().spliterator(),false).map(restaurant -> restaurantToDTO(restaurant)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение ресторана по ID")
    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantDTO getRestaurantById(@PathVariable Integer restaurantId){
        Optional<Restaurant> row = restaurantRepository.findById(restaurantId);
        if(row.isPresent()) {
            Restaurant restaurant = row.get();
            return restaurantToDTO(restaurant);
        } else {
            throw new NoSuchElementException();
        }
    }

    public RestaurantDTO restaurantToDTO (Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getAddress(), restaurant.getStatus());
    }
}
