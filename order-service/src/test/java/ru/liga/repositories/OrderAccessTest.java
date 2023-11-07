package ru.liga.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Order;
import ru.liga.entities.OrderItem;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import static ru.liga.entities.OrderStatus.CUSTOMER_PAID;
import static ru.liga.entities.OrderStatus.KITCHEN_ACCEPTED;

@SpringBootTest
public class OrderAccessTest {

    @Autowired
    OrderRepository orderRepository;

    Logger logger = LogManager.getLogger(OrderAccessTest.class);

    @Test
    public void whenGetExistsOrderByIdFromRepositoryThenReturnEntity(){
        try {
            Order order = orderRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(CUSTOMER_PAID, order.getStatus());
        } catch (NoSuchElementException e) {
            logger.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewOrderEntityByIdFromRepositoryThenReturnEntity() {
        try {
            OrderItem newItem = new OrderItem(1, 1, 567.8, 2);
            Order newOrder = new Order(1, 1, KITCHEN_ACCEPTED, 1, LocalDateTime.now());
            orderRepository.save(newOrder);
            Order order = orderRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(KITCHEN_ACCEPTED, order.getStatus());
            logger.info("Order success add and get from repository");
        } catch (NoSuchElementException e) {
            logger.error("No order found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistOrderItemByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            Order order = orderRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}
