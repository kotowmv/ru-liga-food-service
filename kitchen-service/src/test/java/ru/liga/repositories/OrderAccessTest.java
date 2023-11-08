package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Order;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import static ru.liga.entities.OrderStatus.CUSTOMER_PAID;
import static ru.liga.entities.OrderStatus.KITCHEN_ACCEPTED;

@Slf4j
@SpringBootTest
public class OrderAccessTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void whenGetExistsOrderByIdFromRepositoryThenReturnEntity(){
        try {
            UUID id = UUID.fromString("acb5c70f-a244-4d45-a148-7b5832f9d5a0");
            Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(CUSTOMER_PAID, order.getStatus());
        } catch (NoSuchElementException e) {
            log.error("No item found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewOrderEntityByIdFromRepositoryThenReturnEntity() {
        try {
            UUID id = UUID.fromString("fba381c3-913a-4cf7-a501-16a4e7e18b3d");
            Order newOrder = new Order(id,1, 1, KITCHEN_ACCEPTED, 1, LocalDateTime.now());
            orderRepository.save(newOrder);
            Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals(KITCHEN_ACCEPTED, order.getStatus());
            log.info("Order success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No order found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistOrderItemByIdThenThrowError() {
        UUID fake_id = UUID.fromString("11f64e6f-9a0d-4a8d-891e-49c0637d9925");
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            Order order = orderRepository.findById(fake_id).orElseThrow(NoSuchElementException::new);
        });
    }
}
