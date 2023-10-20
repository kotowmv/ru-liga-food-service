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

@Tag(name = "Order service / API для работы с позициями заказа")
@RestController
@RequestMapping("/os")
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

    @Operation(summary = "Добавить позицию в заказ")
    @PostMapping("/orderItem")
    public void addOrderItem(@RequestBody OrderItemDTO itemDTO){
        orderItemRepository.save(orderItemToEntity(itemDTO));
    }

    @Operation(summary = "Удалить позицию в заказе по ID")
    @DeleteMapping ("/orderItem/{orderItemId}")
    public void deleteOrderItem(@PathVariable Integer orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    public OrderItemDTO orderItemToDTO (OrderItem item) {
        return new OrderItemDTO(item.getId(), item.getMenuItemId(), item.getMenuItemId(), item.getPrice(), item.getQuantity());
    }

    public OrderItem orderItemToEntity (OrderItemDTO itemDTO) {
        return new OrderItem(itemDTO.getOrderId(), itemDTO.getMenuItemId(), itemDTO.getPrice(), itemDTO.getQuantity());
    }
}
