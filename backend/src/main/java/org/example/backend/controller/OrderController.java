package org.example.backend.controller;

import jakarta.transaction.Transactional;
import org.example.backend.dto.OrderDto;
import org.example.backend.entity.Cart;
import org.example.backend.entity.Item;
import org.example.backend.entity.Order;
import org.example.backend.repository.CartRepository;
import org.example.backend.repository.ItemRepository;
import org.example.backend.repository.OrderRepository;
import org.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/api/orders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByUserIdOrderByIdDesc(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getId(token);
        Order newOrder = new Order();

        newOrder.setUserId(jwtService.getId(token));
        newOrder.setName(dto.getName());
        newOrder.setPhoneNumber(dto.getPhoneNumber());
        newOrder.setAddress1(dto.getAddress1());
        newOrder.setAddress2(dto.getAddress2());
        newOrder.setPostCode(dto.getPostCode());
        newOrder.setRequest(dto.getRequest());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());

        orderRepository.save(newOrder);
        cartRepository.deleteByUserId(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
