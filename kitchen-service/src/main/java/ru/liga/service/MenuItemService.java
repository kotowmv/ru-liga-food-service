package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.MenuItemMapper;
import ru.liga.dto.MenuItemDTO;
import ru.liga.entities.MenuItem;
import ru.liga.repositories.MenuItemRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public List<MenuItemDTO> dtoList() {
        return StreamSupport.stream(menuItemRepository.findAll().spliterator(), false).map(this::menuItemToDTO).collect(Collectors.toList());
    }

    public MenuItemDTO getById(Integer id) {
        return menuItemRepository.findById(id)
                .map(this::menuItemToDTO)
                .orElseThrow(() -> new RuntimeException("Menu item with id = " + id + "is not exists"));
    }

    public List<MenuItemDTO> dtoList2() throws IOException {
        return menuItemMapper.getMenuItems().stream().map(this::menuItemToDTO).collect(Collectors.toList());
    }

    public void convertAndSave(MenuItemDTO itemDTO) {
        menuItemRepository.save(menuItemToEntity(itemDTO));
    }

    public void deleteById(Integer id) {
        menuItemRepository.deleteById(id);
    }

    public void updatePriceById(Integer id, Double price) {
        Optional<MenuItem> row = menuItemRepository.findById(id);
        if (row.isPresent()) {
            MenuItem item = row.get();
            item.setPrice(price);
            menuItemRepository.save(item);
        } else {
            throw new RuntimeException("Menu item with id = " + id + "is not exists");
        }
    }

    public MenuItemDTO menuItemToDTO(MenuItem item) {
        return new MenuItemDTO(item.getId(), item.getRestaurantId(), item.getName(), item.getPrice(), item.getImage(), item.getDescription());
    }

    public MenuItem menuItemToEntity(MenuItemDTO itemDTO) {
        return new MenuItem(itemDTO.getRestaurantId(), itemDTO.getName(), itemDTO.getPrice(), itemDTO.getImage(), itemDTO.getDescription());
    }
}
