package ru.liga.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "courier_id")
    private Integer courierId;

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
