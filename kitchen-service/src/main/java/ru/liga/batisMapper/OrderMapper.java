package ru.liga.batisMapper;

import org.apache.ibatis.annotations.Mapper;
import ru.liga.entities.Order;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrders();
    List<Order> getOrderById(Integer id);
}
