package org.example.backend.repository;

import org.example.backend.dto.ItemDto;
import org.example.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByIdIn(List<Integer> ids);

    @Query("SELECT i FROM Item i JOIN FETCH i.category")
    List<Item> findAllWithCategory();

    Item save(Item item);
}
