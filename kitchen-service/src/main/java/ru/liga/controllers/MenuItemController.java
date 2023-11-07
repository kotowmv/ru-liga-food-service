package ru.liga.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Получить список позиций меню", description = "Получить список всех существующих позиций в меню ресторана")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/menu_items")
    public List<MenuItemDTO> getMenuItemList() {
        return menuItemService.getDtoList();
    }

    @Operation(summary = "Получить список позиций меню / MyBatis", description = "Получить список всех существующих позиций в меню ресторана (для проверки работоспособности MyBatis)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/menu_items2")
    public List<MenuItemDTO> menuItemList2() {
        return menuItemService.getDtoListWithMyBatis();
    }

    @Operation(summary = "Получить позицию меню по ID", description = "Получить одну позицию меню по ее идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/menu_item/{id}")
    public MenuItemDTO getMenuItemById(@PathVariable Integer id) {
        return menuItemService.getDtoById(id);
    }

    @Operation(summary = "Добавить позицию меню", description = "Добавить новую позицию меню")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/menu_item")
    public void addMenuItem(@RequestBody MenuItemDTO itemDTO) {
        menuItemService.convertAndSave(itemDTO);
    }

    @Operation(summary = "Удалить позицию меню по ID", description = "Удалить существующую позицию меню по ее идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/menu_item/{id}")
    public void deleteMenuItemById(@PathVariable Integer id) {
        menuItemService.deleteById(id);
    }

    @Operation(summary = "Обновить цену позиции меню по ID", description = "Обновить цену существующей позиции меню по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PatchMapping("/menu_item/{id}")
    public void updatePriceMenuItemById(@PathVariable Integer id, @RequestBody ObjectNode json) {
        Double price = json.get("price").asDouble();
        menuItemService.updatePriceById(id, price);
    }
}