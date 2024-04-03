package org.example.backend.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.JwtService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {

        User user = userRepository.findByLoginIdAndPassword(params.get("loginId"), params.get("password"));
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

    @GetMapping("api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);
        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            String name = userService.getNameById(id); // Assuming you have a method to fetch user's name by ID
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", id);
            responseData.put("name", name);
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
    public ResponseEntity join(@RequestBody Map<String, String> params) {
        // 회원가입 양식에서 전송된 데이터 추출
        String name = params.get("name");
        String loginId = params.get("loginId");
        String password = params.get("password");
        String email = params.get("email");

        // 예외 처리: 필수 필드가 누락되었는지 확인
        if (name == null || loginId == null || password == null || email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "모든 필드는 필수입니다.");
        }

        // 이미 존재하는 사용자인지 확인
        User existingUser = userRepository.findByLoginId(loginId);
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.");
        }

        // 사용자 객체 생성 및 저장
        User newUser = new User();
        newUser.setName(name);
        newUser.setLoginId(loginId);
        newUser.setPassword(password);
        newUser.setEmail(email);

        // 사용자 저장
        userRepository.save(newUser);

        // 회원가입 성공 응답 반환
        return new ResponseEntity<>("회원가입이 성공적으로 완료되었습니다.", HttpStatus.OK);
    }
}
