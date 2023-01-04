package com.dano.kjm.domain.seller.api;

import com.dano.kjm.domain.seller.application.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public String changeRole(@RequestBody String email) {
        sellerService.savedSeller(email);
        return "redirect:/";
    }

    @DeleteMapping("/{email}")
    public String deleteRole(@PathVariable String email) {
        sellerService.deleteSeller(email);
        return "redirect:/";
    }
}
