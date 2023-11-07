package ru.liga.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.OrderItem;
import java.util.NoSuchElementException;

@SpringBootTest
class OrderItemAccessTest {

    @Autowired
    OrderItemRepository orderItemRepository;

    Logger logger = LogManager.getLogger(OrderItemAccessTest.class);

    @Test
    public void whenGetExistsOrderItemByIdFromRepositoryThenReturnEntity(){
        try {
            OrderItem item = orderItemRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(200.0, item.getPrice());
        } catch (NoSuchElementException e) {
            logger.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewOrderItemEntityByIdFromRepositoryThenReturnEntity() {
        try {
            OrderItem newItem = new OrderItem(1, 1, 567.8, 2);
            orderItemRepository.save(newItem);
            OrderItem item = orderItemRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(567.8, item.getPrice());
            logger.info("Order item success add and get from repository");
        } catch (NoSuchElementException e) {
            logger.error("No order item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
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