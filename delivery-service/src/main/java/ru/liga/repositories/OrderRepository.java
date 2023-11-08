package ru.liga.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entities.Order;
import ru.liga.entities.OrderStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

    Optional<Order> findOrderByCustomerId(Integer customerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = :status WHERE id = :id", nativeQuery = true)
    void updateOrderStatusById(@Param("id") UUID id,
                               @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET courier_id = :courierId WHERE id = :id", nativeQuery = true)
    void setCourierById(@Param("id") UUID orderId,
                        @Param("courierId") Integer courierId);

    List<Order> findOrdersByStatus(OrderStatus status);
}
