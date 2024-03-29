package com.project.elicegym.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class categoryController {

    @GetMapping("/category-add")
    public String categoryAdd(){
        return "category/category-add";
    }

    @GetMapping("/category-management")
    public String categoryManagement(){
        return "category/category-management";
    }
}
