package ru.liga.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.entities.Customer;
import java.util.NoSuchElementException;

@Slf4j
@SpringBootTest
class CustomerAccessTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void whenGetExistsCustomerByIdFromRepositoryThenReturnEntity() {
        try {
            Customer customer = customerRepository.findById(1).orElseThrow(NoSuchElementException::new);
            Assertions.assertEquals("test1@mail.com", customer.getEmail());
        } catch (NoSuchElementException e) {
            log.error("No customer found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
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
            log.info("Customer success add and get from repository");
        } catch (NoSuchElementException e) {
            log.error("No customer found");
            Assertions.fail();
        } catch (NullPointerException e) {
            log.error("Repository access error");
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