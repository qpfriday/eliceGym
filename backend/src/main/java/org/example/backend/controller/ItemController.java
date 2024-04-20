package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.dto.ItemDto;
import org.example.backend.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<ItemDto>> itemList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDto> items = itemService.getAllItems(pageable);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/api/item/create")
    public ResponseEntity<String> createItem(@RequestPart("item") ItemCreateDto itemCreateDto,
                                             @RequestPart("file") MultipartFile file) {
        if (itemCreateDto.getCategoryId() <= 0) {
            throw new IllegalArgumentException("Invalid category ID: " + itemCreateDto.getCategoryId());
        }
        try {
            int itemId = itemService.createItem(itemCreateDto, file);
            return ResponseEntity.ok("Item created successfully with ID: " + itemId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating item: " + e.getMessage());
        }
    }

    @PutMapping("/api/item/update")
    public ResponseEntity<String> updateItem(@RequestBody ItemDto itemDto) {
        itemService.updateItem(itemDto);
        return ResponseEntity.ok("Item updated successfully");
    }

    @DeleteMapping("/api/item/{item_id}")
    public ResponseEntity<String> deleteItem(@PathVariable("item_id") int item_id) {
        try {
            itemService.deleteItemById(item_id);
            return ResponseEntity.ok("Item deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting item with ID {}: {}", item_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting item: " + e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
