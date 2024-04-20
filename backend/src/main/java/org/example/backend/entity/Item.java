package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.dto.ItemDto;

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

    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;
    @Column(nullable = false)
    private int price;
    @Column
    private int discountPer;
    @Column(length = 25, nullable = false)
    private String selection;
    @Column(length = 200, nullable = false)
    private String description;
    @Column
    private int stock;
    @Column
    private int deliveryPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public void updateDetails(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.imgPath = itemDto.getImgPath();
        this.price = itemDto.getPrice();
        this.discountPer = itemDto.getDiscountPer();
        this.selection = itemDto.getSelection();
        this.description = itemDto.getDescription();
        this.stock = itemDto.getStock();
        this.deliveryPrice = itemDto.getDeliveryPrice();
    }
}
