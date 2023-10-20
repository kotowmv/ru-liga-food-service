package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    @Transactional(readOnly = true)
    @Query("select item from OrderItem item where item.menuItemId = :menuItemId")
    OrderItem findOrderItemByMenuItemId(@Param("menuItemId") String menuItemId);
}
