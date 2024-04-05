package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.dto.ItemCreateDto;
import org.example.backend.entity.Item;
import org.example.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public int createItem(ItemCreateDto itemCreateDto) {
        log.info("itemCreateDto : {}", itemCreateDto);
        Item newItem = Item.createItem(itemCreateDto);
        itemRepository.save(newItem);
        return newItem.getId();
    }

    public Item getItem(int item_id){
        return itemRepository.findById(item_id).orElseThrow();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
