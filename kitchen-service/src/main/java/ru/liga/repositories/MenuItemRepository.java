package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    @Transactional(readOnly = true)
    @Query("select item from MenuItem item where item.name = :name")
    MenuItem findMenuItemByName(@Param("name") String name);
}
