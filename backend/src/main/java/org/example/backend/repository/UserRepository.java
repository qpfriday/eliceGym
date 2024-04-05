package org.example.backend.repository;

import org.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLoginIdAndPassword(String loginId, String password);

    User findByLoginId(String loginId);

    User findById(int id);
}
