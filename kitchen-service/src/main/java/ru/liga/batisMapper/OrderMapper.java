package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ru.liga.entities.Order;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface OrderMapper {
    List<Order> getOrders();
    Optional<Order> getOrderById(Integer id);
}
