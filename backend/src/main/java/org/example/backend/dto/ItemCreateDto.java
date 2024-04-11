package org.example.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateDto {

    private String name;
    private String imgPath;
    private int price;
    private int discountPer;
    private String parentCategory;
    private String childCategory;
    private String selection;
    private String description;
    private int stock;
    private int deliveryPrice;
}
