package org.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.Item;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemDto {


    private int id;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


    public ItemDto(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.imgPath = item.getImgPath();
        this.price = item.getPrice();
        this.discountPer = item.getDiscountPer();
        this.parentCategory = item.getParentCategory();
        this.childCategory = item.getChildCategory();
        this.selection = item.getSelection();
        this.description = item.getDescription();
        this.stock = item.getStock();
        this.deliveryPrice = item.getDeliveryPrice();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }
}
