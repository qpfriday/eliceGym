package org.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String loginId;
    private String password;
    private String email;
    private String deliveryAddress;
    private String phoneNumber;
}
