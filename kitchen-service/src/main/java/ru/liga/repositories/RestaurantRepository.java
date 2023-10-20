package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    @Transactional(readOnly = true)
    @Query("select restaurant from Restaurant restaurant where restaurant.status = :status")
    Restaurant findRestaurantByStatus(@Param("status") String status);
}
