package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entities.OrderItem;
import ru.liga.repositories.OrderItemRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Kitchen service / API для работы с позициями заказа")
@RestController
@RequestMapping("/ks")
public class OrderItemController {
    OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Operation(summary = "Список позиций в заказе")
    @GetMapping("/orderItemList")
    public List<OrderItemDTO> orderItemList(){
        List<OrderItemDTO> list = StreamSupport.stream(orderItemRepository.findAll().spliterator(), false).map(item -> orderItemToDTO(item)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение позиции заказа по ID")
    @GetMapping("/orderItem/{orderItemId}")
    public OrderItemDTO getOrderItemById(@PathVariable Integer orderItemId){
        Optional<OrderItem> row = orderItemRepository.findById(orderItemId);
        if(row.isPresent()) {
            OrderItem item = row.get();
            return orderItemToDTO(item);
        } else {
            throw new NoSuchElementException();
        }
    }

    public OrderItemDTO orderItemToDTO (OrderItem item) {
        return new OrderItemDTO(item.getId(), item.getMenuItemId(), item.getMenuItemId(), item.getPrice(), item.getQuantity());
    }
}
