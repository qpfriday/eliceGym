package org.example.backend.repository;

import org.example.backend.entity.Cart;
import org.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLoginIdAndPassword(String loginId, String password);

    User findByLoginId(String loginId);

    List<User> findById(int Id);

}
