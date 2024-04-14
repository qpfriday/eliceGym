package org.example.backend.repository;

import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByIdIn(List<Integer> ids);

    Item save(Item item);
}
