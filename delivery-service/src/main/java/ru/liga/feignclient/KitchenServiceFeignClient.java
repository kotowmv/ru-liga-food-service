package ru.liga.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.entities.OrderStatus;

@FeignClient(name = "kitchenService", url="${feign.client.config.kitchenService.url}")
public interface KitchenServiceFeignClient {
    @PatchMapping("/order/{id}")
    void updateOrderStatusById(@PathVariable Integer id, @RequestBody OrderStatus status);
}
