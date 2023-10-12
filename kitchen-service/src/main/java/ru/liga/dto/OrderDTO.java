package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO заказа")
public class OrderDTO {
private int id;
private int customer_id;
private int restoraunt_id;
private String status;
private int courier_id;
private String timestamp;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getCustomer_id() {
    return customer_id;
}

public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
}

public int getRestoraunt_id() {
    return restoraunt_id;
}

public void setRestoraunt_id(int restoraunt_id) {
    this.restoraunt_id = restoraunt_id;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public int getCourier_id() {
    return courier_id;
}

public void setCourier_id(int courier_id) {
    this.courier_id = courier_id;
}

public String getTimestamp() {
    return timestamp;
}

public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
}
}
