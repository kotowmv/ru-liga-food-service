package ru.liga.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private UUID id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", insertable = false, updatable = false)
    private Courier courier;
    @Column(name = "courier_id")
    private Integer courierId;

    private LocalDateTime timestamp;

    public Order(UUID id, Integer customerId, Integer restaurantId, OrderStatus status, Integer courierId, LocalDateTime timestamp) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.courierId = courierId;
        this.timestamp = timestamp;
    }
}
