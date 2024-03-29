package com.project.elicegym.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 에 있는 id 생성 전략을 따른다
    private int id;

    @Column(unique = true)
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String roll; // admin
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
