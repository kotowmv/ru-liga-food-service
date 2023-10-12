package ru.liga.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes ={OrderController.class})
public class OrderExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Requested order does not exist"
    )
    public void orderNotFoundException() {}
}
