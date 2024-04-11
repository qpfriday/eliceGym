package org.example.backend.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.dto.UserDto;
import org.example.backend.entity.User;
import org.example.backend.repository.ItemRepository;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.JwtService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ItemRepository itemRepository;


    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {

        User user = userRepository.findByLoginIdAndPassword(params.get("loginId"), params.get("password"));
        System.out.println(params.get("loginId") + params.get("password"));
        if (user != null) {
            int id = user.getId();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);
        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", id);
            responseData.put("name", userService.getNameById(id));
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/join")
    public ResponseEntity join(@RequestBody UserDto userDto) {

        userService.join(userDto);

        return new ResponseEntity<>("회원가입이 성공적으로 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/api/account/user")
    public ResponseEntity getUser(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getId(token);
        User user = userRepository.findById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/api/account/info")
    public User getAccountInfo(@CookieValue(value = "token", required = false) String token) {
        return userRepository.findById(jwtService.getId(token));
    }

    @PutMapping("/api/account/update")
    public ResponseEntity updateAccountInfo(@RequestBody UserDto updatedUser) {
        User user = userRepository.findByEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setDeliveryAddress(updatedUser.getDeliveryAddress());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
