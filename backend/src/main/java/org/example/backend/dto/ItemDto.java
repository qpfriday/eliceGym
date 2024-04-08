package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//chile_category
//:
//        "clothing"


@Setter
@Getter
@AllArgsConstructor
public class ItemDto {
    private int stock;
    private String selection;
    private int price;
    private String parent_category;
    private String name;
    private String imgPath;
    private int discount_per;
    private String description;
    private int delivery_price;
    private String chile_category;
}
