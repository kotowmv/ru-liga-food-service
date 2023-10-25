package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import ru.liga.entities.Order;
import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {
    List<Order> getOrders();
    Optional<Order> getOrderById(Integer id);
}
