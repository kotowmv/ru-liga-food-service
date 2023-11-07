package ru.liga.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Customer;
import java.util.NoSuchElementException;

@SpringBootTest
class CustomerAccessTest {

    @Autowired
    CustomerRepository customerRepository;

    Logger logger = LogManager.getLogger(CustomerAccessTest.class);

    @Test
    public void whenGetExistsCustomerByIdFromRepositoryThenReturnEntity() {
        try {
            Customer customer = customerRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("test1@mail.com", customer.getEmail());
        } catch (NoSuchElementException e) {
            logger.error("No customer found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenAddAndGetNewCustomerEntityByIdFromRepositoryThenReturnEntity() {
        try {
            Customer newCustomer = new Customer("+79109998877", "accesstest@mail.com", "AccessTestAddress_1");
            customerRepository.save(newCustomer);
            Customer customer = customerRepository.findById(4).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("accesstest@mail.com", customer.getEmail());
            logger.info("Customer success add and get from repository");
        } catch (NoSuchElementException e) {
            logger.error("No customer found");
            Assertions.fail();
        } catch (NullPointerException e) {
            logger.error("Repository access error");
            Assertions.fail();
        }
    }

    @Test
    public void whenGetNonExistCustomerByIdThenThrowError() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            Customer customer = customerRepository.findById(999).orElseThrow(NoSuchElementException::new);
        });
    }
}