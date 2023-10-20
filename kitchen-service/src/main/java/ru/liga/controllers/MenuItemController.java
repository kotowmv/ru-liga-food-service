package ru.liga.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.batisMapper.MenuItemMapper;
import ru.liga.dto.MenuItemDTO;
import ru.liga.entities.MenuItem;
import ru.liga.repositories.MenuItemRepository;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Kitchen service / API для работы с позициями меню")
@RestController
@RequestMapping("/ks")
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

    @Operation(summary = "Добавить объект в меню ресторана")
    @PostMapping("/menuItem")
    public void addMenuItem(@RequestBody MenuItemDTO itemDTO) {
        menuItemRepository.save(menuItemToEntity(itemDTO));
    }

    @Operation(summary = "Удалить объект в меню ресторана по ID")
    @DeleteMapping ("/menuItem/{menuItemId}")
    public void deleteMenuItemById(@PathVariable Integer menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }

    @Operation(summary = "Обновить цену объекта в меню ресторана по ID")
    @PatchMapping("/menuItem/{menuItemId}")
    public void updatePriceMenuItemById(@PathVariable Integer menuItemId, @RequestBody ObjectNode json) {
        Optional<MenuItem> row = menuItemRepository.findById(menuItemId);
        Double price = json.get("price").asDouble();
        if(row.isPresent()) {
            MenuItem item = row.get();
            item.setPrice(price);
            menuItemRepository.save(item);
        }
    }

    // MyBatis mapping
    // NOT WORK
    SqlSessionFactory sqlSessionFactory;
    MenuItemMapper menuItemMapper;
    Reader reader;

    @Operation(summary = "Список заказов")
    @GetMapping("/menuItemList2")
    public List<MenuItem> menuItemList2() throws IOException {
        reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        menuItemMapper = sqlSessionFactory.openSession().getMapper(MenuItemMapper.class);
        List<MenuItem> list = menuItemMapper.getMenuItems();
        return list;
    }

    public MenuItemDTO menuItemToDTO (MenuItem item) {
        return new MenuItemDTO(item.getId(), item.getRestaurantId(), item.getName(),item.getPrice(),item.getImage(), item.getDescription());
    }

    public MenuItem menuItemToEntity (MenuItemDTO itemDTO) {
        return new MenuItem(itemDTO.getRestaurantId(), itemDTO.getName(), itemDTO.getPrice(), itemDTO.getImage(), itemDTO.getDescription());
    }
}