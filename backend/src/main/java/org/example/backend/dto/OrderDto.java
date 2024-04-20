package org.example.backend.dto;

import lombok.Getter;

@Getter
public class OrderDto {
    private String name;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String postCode;
    private String request;
    private String payment;
    private String cardNumber;
    private String items;
}
