package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.dto.ItemDto;
import org.example.backend.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/item/{item_id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("item_id") int item_id) {
        ItemDto itemDto = itemService.getItemDtoById(item_id);
        return ResponseEntity.ok(itemDto);
    }

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemDto>> itemList() {
        List<ItemDto> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/api/item/create")
    public ResponseEntity<String> createItem(@RequestPart("item") ItemCreateDto dto, @RequestPart("file") MultipartFile file) throws IOException {
        log.info("Creating item with file: {}", dto);
        int itemId = itemService.createItem(dto, file);
        return ResponseEntity.ok("Item created successfully with ID: " + itemId);
    }

    @PutMapping("/api/item/update")
    public ResponseEntity<String> updateItem(@RequestBody ItemDto itemDto) {
        itemService.updateItem(itemDto);
        return ResponseEntity.ok("Item updated successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
