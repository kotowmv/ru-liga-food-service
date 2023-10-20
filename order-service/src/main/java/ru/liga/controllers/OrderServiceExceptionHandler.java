package ru.liga.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes ={CustomerController.class, MenuItemController.class, OrderController.class, OrderItemController.class, RestaurantController.class})
public class OrderServiceExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Requested object does not exist"
    )
    public void notFoundException() {}
}
