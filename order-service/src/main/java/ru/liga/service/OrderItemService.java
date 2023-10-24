package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entities.OrderItem;
import ru.liga.repositories.OrderItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItemDTO> dtoList () {
        return StreamSupport.stream(orderItemRepository.findAll().spliterator(), false).map(this::orderItemToDTO).collect(Collectors.toList());
    }

    public OrderItemDTO getById (Integer id) {
        return orderItemRepository.findById(id)
                .map(this::orderItemToDTO)
                .orElseThrow(() -> new RuntimeException("Order item with id = " + id + "is not exists"));
    }

    public void convertAndSave (OrderItemDTO orderItemDTO) {
        orderItemRepository.save(orderItemToEntity(orderItemDTO));
    }

    public void deleteById (Integer id) {
        orderItemRepository.deleteById(id);
    }

    public OrderItemDTO orderItemToDTO (OrderItem item) {
        return new OrderItemDTO(item.getId(), item.getMenuItemId(), item.getMenuItemId(), item.getPrice(), item.getQuantity());
    }

    public OrderItem orderItemToEntity (OrderItemDTO itemDTO) {
        return new OrderItem(itemDTO.getOrderId(), itemDTO.getMenuItemId(), itemDTO.getPrice(), itemDTO.getQuantity());
    }
}
