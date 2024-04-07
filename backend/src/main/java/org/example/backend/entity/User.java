package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String loginId;
    @Column(length = 50, nullable = false)
    private String phoneNumber;
    @Column(length = 500)
    private String deliveryAddress;
    @Column(length = 10)
    private String role;
    @Column(length = 50)
    private String createdAt;
    @Column(length = 50)
    private String updatedAt;
}
