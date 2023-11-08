package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Courier;
import java.util.NoSuchElementException;
import static ru.liga.entities.CourierStatus.PENDING;

@Slf4j
@SpringBootTest
class CourierAccessTest {

    @Autowired
    CourierRepository courierRepository;

    @Test
    public void whenGetExistsCustomerByIdFromRepositoryThenReturnEntity() {
        try {
            Courier courier = courierRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("+79101234567", courier.getPhone());
        } catch (NoSuchElementException e) {
            log.error("No courier found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewCustomerEntityByIdFromRepositoryThenReturnEntity() {
        try {
            Courier newCourier = new Courier("+79998887766", PENDING, "27.500, 25.500");
            courierRepository.save(newCourier);
            Courier courier = courierRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("+79998887766", courier.getPhone());
            log.info("Courier success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No courier found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistCustomerByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            Courier courier = courierRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}