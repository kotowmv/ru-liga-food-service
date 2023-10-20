package ru.liga.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Transactional(readOnly = true)
    @Query("select order from Order order where order.customerId = :customerId")
    Order findOrderByCustomerId(@Param("customerId") Integer customerId);
}
