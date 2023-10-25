package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CustomerDTO;
import ru.liga.service.CustomerService;
import java.util.List;

@Tag(name = "Order service / API для работы с клиентами")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Список клиентов")
    @GetMapping("/customers")
    public List<CustomerDTO> customerList(){
        return customerService.dtoList();
    }

    @Operation(summary = "Получение клиента по ID")
    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id){
        return customerService.getDtoById(id);
    }

    @Operation(summary = "Добавить нового клиента")
    @PostMapping("/customer")
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.convertAndSave(customerDTO);
    }

    @Operation(summary = "Удалить клиента по ID")
    @DeleteMapping ("/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteById(id);
    }
}
