package org.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.Item;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemDto {


    private int id;
    private String name;
    private String imgPath;
    private byte[] img;
    private int price;
    private int discountPer;
    private String selection;
    private String description;
    private int stock;
    private int deliveryPrice;
    private int categoryId;
    private String categoryName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


    public ItemDto(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.img = item.getImg();
        this.imgPath = item.getImgPath();
        this.price = item.getPrice();
        this.discountPer = item.getDiscountPer();
        this.selection = item.getSelection();
        this.description = item.getDescription();
        this.stock = item.getStock();
        this.deliveryPrice = item.getDeliveryPrice();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
        if (item.getCategory() != null) {
            this.categoryId = item.getCategory().getId();
            this.categoryName = item.getCategory().getName();
        }
    }
}
