package com.project.elicegym.user.repository;

import com.project.elicegym.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> { // <받아올 Entity, id 의 데이터 타입>
    boolean existsByUsername(String username);
}
