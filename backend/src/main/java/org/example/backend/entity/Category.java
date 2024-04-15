package org.example.backend.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column
    private String createdAt;

    @Column
    private String updatedAt;

    @Builder
    public Category(String name, String description, String createdAt, String updatedAt){
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String name, String description, String updatedAt){
        this.name = name;
        this.description = description;
        this.updatedAt = updatedAt;
    }
}