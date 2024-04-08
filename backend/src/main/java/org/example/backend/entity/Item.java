package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.dto.ItemCreateDto;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item extends BaseTime{

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
    private String childCategory;
    @Column(length = 25, nullable = false)
    private String selection;
    @Column(length = 200, nullable = false)
    private String description;
    @Column
    private int stock;
    @Column
    private int deliveryPrice;




    public static Item createItem(ItemCreateDto dto){
        Item item = new Item();
        item.name = dto.getName();
        item.imgPath = dto.getImgPath();
        item.price = dto.getPrice();
        item.discountPer = dto.getDiscountPer();
        item.parentCategory = dto.getParentCategory();
        item.childCategory = dto.getChildCategory();
        item.selection = dto.getSelection();
        item.description = dto.getDescription();
        item.stock = dto.getStock();
        item.deliveryPrice = dto.getDeliveryPrice();
        return item;
    }
}
