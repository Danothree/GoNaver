package com.dano.kjm.controller;

import com.dano.kjm.dto.request.MemberUpdateDto;
import com.dano.kjm.dto.request.SignUpDto;
import com.dano.kjm.dto.response.MemberDetail;
import com.dano.kjm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return "member/login";
    }

    @GetMapping("/login/error")
    public String signInError(HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "member/signUp";
        }
        try {
            memberService.saveMember(signUpDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signUp";
        }
        return "redirect:/";
    }

    @PatchMapping
    public String update(@Valid @RequestBody MemberUpdateDto memberUpdateDto) {
        memberService.updateMember(memberUpdateDto);
        return "redirect:/";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable("email") String email) {
        memberService.deleteMember(email);
        return "redirect:/";
    }

    @GetMapping("/{email}")
    public String update(@PathVariable("email") String email, Model model){
        MemberDetail memberDetail = memberService.findMember(email);
        model.addAttribute("memberDetail", memberDetail);
        return "member/detail";
    }

}
