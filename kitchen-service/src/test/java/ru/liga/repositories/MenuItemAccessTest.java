package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.MenuItem;
import java.util.NoSuchElementException;

@Slf4j
@SpringBootTest
class MenuItemAccessTest {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Test
    public void whenGetExistsMenuItemByIdFromRepositoryThenReturnEntity(){
        try {
            MenuItem item = menuItemRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("meat", item.getName());
        } catch (NoSuchElementException e) {
            log.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewMenuItemEntityByIdFromRepositoryThenReturnEntity() {
        try {
            MenuItem newItem = new MenuItem(1,"salad",555.5,"url","description");
            menuItemRepository.save(newItem);
            MenuItem item = menuItemRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("salad", item.getName());
            log.info("Menu item success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No menu item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistMenuItemByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            MenuItem item = menuItemRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}