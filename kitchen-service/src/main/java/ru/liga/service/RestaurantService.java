package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entities.Restaurant;
import ru.liga.repositories.RestaurantRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> dtoList(){
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(),false).map(this::restaurantToDTO).collect(Collectors.toList());
    }

    public RestaurantDTO getById(Integer id) {
        return restaurantRepository.findById(id)
                .map(this::restaurantToDTO)
                .orElseThrow(() -> new RuntimeException("Restaurant with id = " + id + "is not exists"));
    }

    public void convertAndSave (RestaurantDTO restaurantDTO) {
        restaurantRepository.save(restaurantToEntity(restaurantDTO));
    }

    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public RestaurantDTO restaurantToDTO (Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getAddress(), restaurant.getStatus());
    }

    public Restaurant restaurantToEntity (RestaurantDTO restaurantDTO) {
        return new Restaurant(restaurantDTO.getAddress(), restaurantDTO.getStatus());
    }
}
