package ru.liga.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.MenuItemDTO;
import ru.liga.service.MenuItemService;
import java.util.List;

@Tag(name = "Kitchen service / API для работы с позициями меню")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Operation(summary = "Список объектов в меню ресторана")
    @GetMapping("/menu_items")
    public List<MenuItemDTO> getMenuItemList(){
        return menuItemService.dtoList();
    }

    @Operation(summary = "Список объектов в меню ресторана / MyBatis")
    @GetMapping("/menu_items2")
    public List<MenuItemDTO> menuItemList2(){
        return menuItemService.dtoList2();
    }

    @Operation(summary = "Получение объекта меню по ID")
    @GetMapping("/menu_item/{id}")
    public MenuItemDTO getMenuItemById(@PathVariable Integer id){
        return menuItemService.getDtoById(id);
    }

    @Operation(summary = "Добавить объект в меню ресторана")
    @PostMapping("/menu_item")
    public void addMenuItem(@RequestBody MenuItemDTO itemDTO) {
        menuItemService.convertAndSave(itemDTO);
    }

    @Operation(summary = "Удалить объект в меню ресторана по ID")
    @DeleteMapping ("/menu_item/{id}")
    public void deleteMenuItemById(@PathVariable Integer id) {
        menuItemService.deleteById(id);
    }

    @Operation(summary = "Обновить цену объекта в меню ресторана по ID")
    @PatchMapping("/menu_item/{id}")
    public void updatePriceMenuItemById(@PathVariable Integer id, @RequestBody ObjectNode json) {
        Double price = json.get("price").asDouble();
        menuItemService.updatePriceById(id, price);
    }
}