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

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    private String name;

    private Double price;

    private String image;

    private String description;
}


