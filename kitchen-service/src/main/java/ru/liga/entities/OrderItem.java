package ru.liga.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @JoinColumn(name = "menu_item_id", insertable = false, updatable = false)
    private MenuItem menuItem;
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    private Double price;

    private Integer quantity;
}
