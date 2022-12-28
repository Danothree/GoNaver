package com.dano.kjm.controller;

import com.dano.kjm.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public String changeRole(String email) {
        sellerService.savedSeller(email);
        return "redirect:/";
    }

    @DeleteMapping("/{email}")
    public String deleteRole(@PathVariable String email) {
        sellerService.deleteSeller(email);
        return "redirect:/";
    }
}
