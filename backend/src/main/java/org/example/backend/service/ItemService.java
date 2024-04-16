package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Category;
import org.example.backend.entity.Item;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public int createItem(ItemCreateDto itemCreateDto, MultipartFile file) throws IOException {
        log.info("Creating item with image file");
        Item newItem = createItemEntity(itemCreateDto);
        if (!file.isEmpty()) {
            newItem.setImg(file.getBytes());
        }
        itemRepository.save(newItem);
        return newItem.getId();
    }

    private Item createItemEntity(ItemCreateDto itemCreateDto) {
        Category category = categoryRepository.findById(itemCreateDto.getCategory_id()).orElseThrow(() ->
                new IllegalArgumentException("Category not found with id: " + itemCreateDto.getCategory_id())
        );

        Item item = new Item();
        item.setName(itemCreateDto.getName());
        item.setImgPath(itemCreateDto.getImgPath());
        item.setPrice(itemCreateDto.getPrice());
        item.setDiscountPer(itemCreateDto.getDiscount_per());
        item.setSelection(itemCreateDto.getSelection());
        item.setDescription(itemCreateDto.getDescription());
        item.setStock(itemCreateDto.getStock());
        item.setDeliveryPrice(itemCreateDto.getDelivery_price());
        item.setCategory(category);

        return item;
    }

    public void updateItem(ItemDto itemDto) {
        Item existingItem = itemRepository.findById(itemDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("Product not found for the ID: " + itemDto.getId())
        );
        existingItem.updateDetails(itemDto);
        if (itemDto.getCategoryId() != 0) {
            Category category = categoryRepository.findById(itemDto.getCategoryId()).orElseThrow(() ->
                    new RuntimeException("Category not found for ID: " + itemDto.getCategoryId())
            );
            existingItem.setCategory(category);
        }
        itemRepository.save(existingItem);
    }

    public ItemDto getItemDtoById(int itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("Item not found for ID: " + itemId)
        );
        return new ItemDto(item);
    }

    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }
}
