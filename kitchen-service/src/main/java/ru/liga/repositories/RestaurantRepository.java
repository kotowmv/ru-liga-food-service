package ru.liga.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entities.Restaurant;
import ru.liga.entities.RestaurantStatus;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Optional<Restaurant> findRestaurantByStatus(RestaurantStatus status);
}
