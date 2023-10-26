package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<RestaurantDTO> getDtoList() {
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(), false).map(this::restaurantToDTO).collect(Collectors.toList());
    }

    public RestaurantDTO getDtoById(Integer id) {
        return restaurantToDTO(getById(id));
    }

    public Restaurant getById(Integer id) {
        if (id > 0) {
            return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant with id = " + id + " is not exists"));
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public void convertAndSave(RestaurantDTO restaurantDTO) {
        restaurantRepository.save(restaurantToEntity(restaurantDTO));
    }

    public void deleteById(Integer id) {
        if (id > 0) {
            try {
                restaurantRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new RuntimeException("Restaurant with id = " + id + " is not exists");
            }
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public RestaurantDTO restaurantToDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getAddress(), restaurant.getStatus());
    }

    public Restaurant restaurantToEntity(RestaurantDTO restaurantDTO) {
        return new Restaurant(restaurantDTO.getAddress(), restaurantDTO.getStatus());
    }
}
