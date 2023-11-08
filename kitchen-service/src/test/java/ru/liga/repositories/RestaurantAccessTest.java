package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Restaurant;
import ru.liga.entities.RestaurantStatus;
import java.util.NoSuchElementException;

@Slf4j
@SpringBootTest
public class RestaurantAccessTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void whenGetExistsOrderItemByIdFromRepositoryThenReturnEntity(){
        try {
            Restaurant restaurant = restaurantRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("first restaurant, 57", restaurant.getAddress());
        } catch (NoSuchElementException e) {
            log.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewOrderItemEntityByIdFromRepositoryThenReturnEntity() {
        try {
            Restaurant newRestaurant = new Restaurant("some adress", RestaurantStatus.OPEN);
            restaurantRepository.save(newRestaurant);
            Restaurant restaurant = restaurantRepository.findById(3).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("some adress", restaurant.getAddress());
            log.info("Restaurant success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No restaurant found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistRestaurantByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            Restaurant restaurant = restaurantRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}
