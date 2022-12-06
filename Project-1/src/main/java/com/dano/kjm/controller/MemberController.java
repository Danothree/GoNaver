package com.dano.kjm.controller;

import com.dano.kjm.dto.request.MemberFormRqDto;
import com.dano.kjm.dto.response.MemberFormRsDto;
import com.dano.kjm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(principal)) {
            model.addAttribute("memberFormRsDto", new MemberFormRsDto());
            return "member/memberForm";
        } else {
            String email = ((UserDetails)principal).getUsername();
            model.addAttribute("memberFormRsDto", memberService.findMember(email));
            return "member/memberForm";
        }
    }

    @PostMapping
    public String signUp(@Valid MemberFormRqDto memberFormRqDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            memberService.saveMember(memberFormRqDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @PatchMapping
    public String update(@Valid @RequestBody MemberFormRqDto memberFormRqDto) {

        memberService.updateMember(memberFormRqDto);
        return "redirect:/";
    }

    @DeleteMapping
    public String delete(@RequestParam String email) {
        memberService.deleteMember(email);
        return "redirect:/";
    }

    public boolean passwordCheck(String pw1, String pw2) {
        return pw1 == pw2;
    }

}
