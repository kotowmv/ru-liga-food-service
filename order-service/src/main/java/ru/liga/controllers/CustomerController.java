package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Получить список клиентов", description = "Получить список всех существующих клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/customers")
    public List<CustomerDTO> customerList() {
        return customerService.getDtoList();
    }

    @Operation(summary = "Получить клиента по ID", description = "Получить одного клиента по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Данные не найдены")
    })
    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id) {
        return customerService.getDtoById(id);
    }

    @Operation(summary = "Добавить клиента", description = "Добавить нового клиента в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные добавлены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @PostMapping("/customer")
    public void addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.convertAndSave(customerDTO);
    }

    @Operation(summary = "Удалить клиента по ID", description = "Удалить существующего клиента по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные удалены"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    })
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteById(id);
    }
}
