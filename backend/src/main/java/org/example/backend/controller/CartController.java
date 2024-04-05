package org.example.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.entity.Cart;
import org.example.backend.entity.Item;
import org.example.backend.repository.CartRepository;
import org.example.backend.repository.ItemRepository;
import org.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
public class CartController {

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
        List<Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId, @RequestParam(name = "quantity", defaultValue = "1") int quantity,
            @CookieValue(value = "token", required = false) String token
    ) {


        log.info("qunatity : {}", quantity);

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getId(token);
        Cart cart = cartRepository.findByUserIdAndItemId(userId, itemId);

        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setItemId(itemId);
            newCart.setQuantity(quantity);
            cartRepository.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getId(token);
        Cart cart = cartRepository.findByUserIdAndItemId(userId, itemId);

        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
