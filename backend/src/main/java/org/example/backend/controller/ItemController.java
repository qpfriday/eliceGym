package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Item;
import org.example.backend.repository.ItemRepository;
import org.example.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {


    private final ItemService itemService;

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("item_id") int item_id){
        Item product = itemService.getItem(item_id);
        return ResponseEntity.ok(new ItemDto(product));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ItemDto>> itemList(){
        List<Item> items = itemService.getAllItems();
        List<ItemDto> result = items.stream().map(ItemDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody ItemCreateDto itemCreateDto){
        itemService.createItem(itemCreateDto);
        return ResponseEntity.ok("success");
    }
}
