package com.project.elicegym;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {
    // http://localhost:8080
    @GetMapping("/")
    public String Hello() {
        return "HelloWorld";
    }
}
