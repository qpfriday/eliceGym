package org.example.backend.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String loginId;
    private String deliveryAddress;
    private String roll;
    private String createdAt;
    private String updatedAt;
}
