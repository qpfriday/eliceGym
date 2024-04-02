package org.example.backend.repository;

import org.example.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);

    Cart findByUserIdAndItemId(int userId, int itemId);

    void deleteByUserId(int userId);
}
