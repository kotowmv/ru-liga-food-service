package ru.liga.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.liga.entities.MenuItem;
import ru.liga.entities.OrderStatus;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    Optional<MenuItem> findMenuItemByName(String name);

    @Modifying
    @Query(value = "UPDATE menu_items SET price = :price WHERE id = :id", nativeQuery = true)
    void updateMenuItemPriceById(@Param("id") Integer id,
                                 @Param("price") Double price);
}
