package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100)
    private String imgPath;
    @Column(nullable = false)
    private int price;
    @Column
    private int discountPer;
    @Column(length = 25, nullable = false)
    private String parentCategory;
    @Column(length = 25, nullable = false)
    private String chileCategory;
    @Column(length = 25, nullable = false)
    private String selection;
    @Column(length = 200, nullable = false)
    private String description;
    @Column
    private int stock;
    @Column
    private int deliveryPrice;
    @Column(length = 50)
    private String createdAt;
    @Column(length = 50)
    private String updatedAt;
}
