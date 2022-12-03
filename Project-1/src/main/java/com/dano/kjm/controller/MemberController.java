package com.dano.kjm.controller;

import com.dano.kjm.dto.MemberFormDto;
import com.dano.kjm.exception.DuplicatedException;
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

    @GetMapping("/login")
    public String signIn() {
        return "member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String signInError(Model model) {
        model.addAttribute("errorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }

    @GetMapping
    public String signUp(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping
    public String signUp(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            memberService.saveMember(memberFormDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @PatchMapping
    public String update(@Valid MemberFormDto memberFormDto, BindingResult bindingResult) {
        return "redirect:/";
    }

    @DeleteMapping
    public String delete(Long memberId) {
        return "redirect:/";
    }

}
