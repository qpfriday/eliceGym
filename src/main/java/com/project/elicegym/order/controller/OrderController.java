package com.project.elicegym.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    // http://localhost:8080/order-payment
    @GetMapping("/order-payment")
    public String OrderPayment() {
        return "order/payment";
    }

    // http://localhost:8080/order-success
    @GetMapping("/order-success")
    public String OrderSuccess() {
        return "order/success";
    }

    // http://localhost:8080/order-list
    @GetMapping("/order-list")
    public String OrderList() {
        return "order/list";
    }

    // http://localhost:8080/order-detail
    @GetMapping("/order-detail")
    public String OrderDetail() {
        return "order/detail";
    }

    // http://localhost:8080/delivery-modify
    @GetMapping("/delivery-modify")
    public String DeliveryModify() {
        return "order/delivery-modify";
    }
}
