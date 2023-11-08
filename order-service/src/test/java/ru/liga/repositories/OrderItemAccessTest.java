package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.OrderItem;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@SpringBootTest
class OrderItemAccessTest {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    public void whenGetExistsOrderItemByIdFromRepositoryThenReturnEntity(){
        try {
            OrderItem item = orderItemRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(200.0, item.getPrice());
        } catch (NoSuchElementException e) {
            log.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewOrderItemEntityByIdFromRepositoryThenReturnEntity() {
        try {
            UUID orderId = UUID.fromString("acb5c70f-a244-4d45-a148-7b5832f9d5a0");
            OrderItem newItem = new OrderItem(orderId, 1, 567.8, 2);
            orderItemRepository.save(newItem);
            OrderItem item = orderItemRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(567.8, item.getPrice());
            log.info("Order item success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No order item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistOrderItemByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            OrderItem item = orderItemRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}