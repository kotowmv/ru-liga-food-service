package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.CustomerDTO;
import ru.liga.entities.Customer;
import ru.liga.repositories.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDTO> dtoList () {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).map(this::customerToDTO).collect(Collectors.toList());
    }

    public CustomerDTO getDtoById(Integer id) {
        return customerToDTO(getById(id));
    }

    public Customer getById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id = " + id + " is not exists"));
    }

    public void convertAndSave (CustomerDTO customerDTO) {
        customerRepository.save(customerToEntity(customerDTO));
    }

    public void deleteById (Integer id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO customerToDTO (Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getPhone(), customer.getEmail(), customer.getAddress());
    }

    public Customer customerToEntity (CustomerDTO customerDTO) {
        return new Customer(customerDTO.getPhone(), customerDTO.getEmail(), customerDTO.getAddress());
    }
}
