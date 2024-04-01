package com.project.elicegym.user.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class JoinDto {
    private String loginId;
    private String password;
    private String name;
    private String email;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
