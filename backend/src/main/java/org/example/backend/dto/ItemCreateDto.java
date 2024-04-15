package org.example.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateDto {

    private String name;
    private MultipartFile img;
    private String imgPath;
    private int price;
    private int discount_per;
    private String parent_category;
    private String child_category;
    private String selection;
    private String description;
    private int stock;
    private int delivery_price;
}
