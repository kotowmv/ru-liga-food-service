package ru.liga.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entities.MenuItem;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    Optional<MenuItem> findMenuItemByName(String name);
}
