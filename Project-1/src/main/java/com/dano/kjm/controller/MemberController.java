package com.dano.kjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/login")
    public String loginForm() {
        return "member/memberLoginForm";
    }

    @GetMapping("/new")
    public String NewForm() {
        return "member/memberForm";
    }


}
