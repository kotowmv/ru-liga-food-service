package ru.liga.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    private String name;

    private Double price;

    private String image;

    private String description;

    public MenuItem(Integer restaurantId, String name, Double price, String image, String description) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }
}


