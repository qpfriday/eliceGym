package com.project.elicegym.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 에 있는 id 생성 전략을 따른다
    private int id;

    @Column(unique = true)
    private String username;
    private String password;
    private String roll; // admin
}
