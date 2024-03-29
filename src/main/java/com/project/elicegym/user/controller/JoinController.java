package com.project.elicegym.user.controller;

import com.project.elicegym.user.dto.JoinDto;
import com.project.elicegym.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    // http://localhost:8080/join
    @GetMapping("/join")
    public String Login() {
        return "user/join";
    }


    @PostMapping("/joinProc") // post 요청으로 데이터가 join 페이지에서 날라옴
    public String joinProcess(JoinDto joinDto) {

        joinService.joinProcess(joinDto);

        return "redirect:/login"; // 회원가입이 끝나면 로그인 페이지로 이동
    }
}
