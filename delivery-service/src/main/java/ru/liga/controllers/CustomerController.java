package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CustomerDTO;
import ru.liga.entities.Customer;
import ru.liga.repositories.CustomerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "Delivery service / API для работы с клиентами")
@RestController
@RequestMapping("/ds")
public class CustomerController {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Operation(summary = "Список клиентов")
    @GetMapping("/customerList")
    public List<CustomerDTO> customerList(){
        List<CustomerDTO> list = StreamSupport.stream(customerRepository.findAll().spliterator(), false).map(customer -> customerToDTO(customer)).collect(Collectors.toList());
        return list;
    }

    @Operation(summary = "Получение клиента по ID")
    @GetMapping("/customer/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Integer customerId){
        Optional<Customer> row = customerRepository.findById(customerId);
        if(row.isPresent()) {
            Customer customer = row.get();
            return customerToDTO(customer);
        } else {
            throw new NoSuchElementException();
        }
    }

    public CustomerDTO customerToDTO (Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getPhone(), customer.getEmail(), customer.getAddress());
    }
}
