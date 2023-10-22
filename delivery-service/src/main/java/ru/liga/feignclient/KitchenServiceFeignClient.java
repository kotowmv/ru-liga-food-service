package ru.liga.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.entities.OrderStatus;

@FeignClient(name = "kitchenService", url = "http://localhost:8081/ks")
public interface KitchenServiceFeignClient {
    @PatchMapping("/order/{orderId}")
    void updateOrderStatusById(@PathVariable Integer orderId, @RequestBody OrderStatus status);
}
