package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Item;
import org.example.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public int createItem(ItemCreateDto itemCreateDto) {
        log.info("itemCreateDto : {}", itemCreateDto);
        Item newItem = Item.createItem(itemCreateDto);
        itemRepository.save(newItem);
        return newItem.getId();
    }

    public int createItem(ItemCreateDto itemCreateDto, MultipartFile file) throws IOException {
        Item newItem = Item.createItem(itemCreateDto);
        newItem.setImg(file.getBytes());
        itemRepository.save(newItem);
        return newItem.getId();
    }

    public void updateItem(int item_id, ItemDto itemDto) {
        Item item = itemRepository.findById(item_id).orElseThrow(() -> {
            return new IllegalArgumentException("상품을 찾는데 실패하였습니다.");
        });
        item.setName(itemDto.getName());
        item.setImgPath(itemDto.getImgPath());
        item.setPrice(itemDto.getPrice());
        item.setDiscountPer(itemDto.getDiscountPer());
        item.setParentCategory(itemDto.getParentCategory());
        item.setChildCategory(itemDto.getChildCategory());
        item.setSelection(itemDto.getSelection());
        item.setDescription(itemDto.getDescription());
        item.setStock(itemDto.getStock());
        item.setDeliveryPrice(itemDto.getDeliveryPrice());
        itemRepository.save(item);
    }

    public void deleteItem(int item_id) {
        itemRepository.deleteById(item_id);
    }
    public Item getItem(int item_id){
        return itemRepository.findById(item_id).orElseThrow();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
