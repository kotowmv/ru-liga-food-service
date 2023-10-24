package ru.liga.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entities.OrderItem;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    Optional<OrderItem> findOrderItemByMenuItemId(String menuItemId);
}
