package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.MenuItemDTO;
import ru.liga.entities.MenuItem;
import ru.liga.repositories.MenuItemRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Order service / API для работы с позициями меню")
@RestController
@RequestMapping("/os")
public class MenuItemController {
    MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Operation(summary = "Список объектов в меню ресторана")
    @GetMapping("/menuItemList")
    public List<MenuItemDTO> getMenuItemList(){
        List<MenuItemDTO> list = StreamSupport.stream(menuItemRepository.findAll().spliterator(),false).map(item -> menuItemToDTO(item)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение объекта меню по ID")
    @GetMapping("/menuItem/{menuItemId}")
    public MenuItemDTO getMenuItemById(@PathVariable Integer menuItemId){
        Optional<MenuItem> row = menuItemRepository.findById(menuItemId);
        if(row.isPresent()) {
            MenuItem item = row.get();
            return menuItemToDTO(item);
        } else {
            throw new NoSuchElementException();
        }
    }

    public MenuItemDTO menuItemToDTO (MenuItem item) {
        return new MenuItemDTO(item.getId(), item.getRestaurantId(), item.getName(),item.getPrice(),item.getImage(), item.getDescription());
    }
}