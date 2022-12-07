package com.dano.kjm.controller;

import com.dano.kjm.dto.request.SignUpDto;
import com.dano.kjm.dto.response.MemberDetail;
import com.dano.kjm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String signIn() {
        return "member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String signInError(Model model) {
        model.addAttribute("errorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("SignUpDTO", new SignUpDto());
        return "member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpDto signUpDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "member/signUp";
        }
        try {
            memberService.saveMember(signUpDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signUp";
        }
        return "redirect:/";
    }

    @PatchMapping
    public String update(@Valid @RequestBody MemberDetail memberDetail) {
        memberService.updateMember(memberDetail);
        return "redirect:/";
    }

    @DeleteMapping
    public String delete(@RequestParam String email) {
        memberService.deleteMember(email);
        return "redirect:/";
    }

}
