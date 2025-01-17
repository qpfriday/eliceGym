package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int userId;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String phoneNumber;
    @Column(length = 500, nullable = false)
    private String address1;
    @Column(length = 500, nullable = false)
    private String address2;
    @Column(length = 500, nullable = false)
    private String postCode;
    @Column(length = 100, nullable = false)
    private String request;
    @Column(length = 10, nullable = false)
    private String payment;
    @Column(length = 16)
    private String cardNumber;
    @Column(length = 5000, nullable = false)
    private String items;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
