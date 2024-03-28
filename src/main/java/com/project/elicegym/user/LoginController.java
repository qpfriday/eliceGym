package com.project.elicegym.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // http://localhost:8080/login
    @GetMapping("/login")
    public String Login() {
        return "user/login";
    }
}
