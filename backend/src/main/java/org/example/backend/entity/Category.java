package org.example.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
    public Category(String name, String desc){
        this.name = name;
        this.description = desc;
    }

    public void update(String name, String desc){
        this.name = name;
        this.description = desc;
    }
}