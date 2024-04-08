package org.example.backend.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Item;
import org.example.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @PostMapping("/api/add/item")
    public ResponseEntity addItem(@RequestBody Map<String, String> params)
    {
        Item new_item = new Item();

        new_item.setName(params.get("name"));
        new_item.setImgPath(params.get("imgPath"));
        new_item.setPrice(Integer.parseInt(params.get("price")));
        new_item.setDiscountPer(Integer.parseInt(params.get("discount_per")));
        new_item.setParentCategory(params.get("parent_category"));
        new_item.setChileCategory(params.get("chile_category"));
        new_item.setSelection(params.get("selection"));
        new_item.setDescription(params.get("description"));
        new_item.setStock(Integer.parseInt(params.get("stock")));
        new_item.setDeliveryPrice(Integer.parseInt(params.get("delivery_price")));

        itemRepository.save(new_item);

        return new ResponseEntity(HttpStatus.OK);
    }
}
