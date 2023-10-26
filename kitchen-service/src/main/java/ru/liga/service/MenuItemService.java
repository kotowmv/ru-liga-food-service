package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.MenuItemMapper;
import ru.liga.dto.MenuItemDTO;
import ru.liga.entities.MenuItem;
import ru.liga.repositories.MenuItemRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public List<MenuItemDTO> getDtoList() {
        return StreamSupport.stream(menuItemRepository.findAll().spliterator(), false).map(this::menuItemToDTO).collect(Collectors.toList());
    }

    public List<MenuItemDTO> getDtoListWithMyBatis() {
        return menuItemMapper.getMenuItems().stream().map(this::menuItemToDTO).collect(Collectors.toList());
    }

    public MenuItemDTO getDtoById(Integer id) {
        return menuItemToDTO(getById(id));
    }

    public MenuItem getById(Integer id) {
        if (id > 0) {
            return menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu item with id = " + id + " is not exists"));
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public void convertAndSave(MenuItemDTO itemDTO) {
        menuItemRepository.save(menuItemToEntity(itemDTO));
    }

    public void deleteById(Integer id) {
        if (id > 0) {
            try {
                menuItemRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new RuntimeException("Courier with id = " + id + " is not exists");
            }
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public void updatePriceById(Integer id, Double price) {
        if (id > 0) {
            Optional<MenuItem> row = menuItemRepository.findById(id);
            if (row.isPresent()) {
                MenuItem item = row.get();
                item.setPrice(price);
                menuItemRepository.save(item);
            } else {
                throw new RuntimeException("Menu item with id = " + id + " is not exists");
            }
        } else {
            throw new RuntimeException("Incorrect id");
        }
    }

    public MenuItemDTO menuItemToDTO(MenuItem item) {
        return new MenuItemDTO(item.getId(), item.getRestaurantId(), item.getName(), item.getPrice(), item.getImage(), item.getDescription());
    }

    public MenuItem menuItemToEntity(MenuItemDTO itemDTO) {
        return new MenuItem(itemDTO.getRestaurantId(), itemDTO.getName(), itemDTO.getPrice(), itemDTO.getImage(), itemDTO.getDescription());
    }
}
