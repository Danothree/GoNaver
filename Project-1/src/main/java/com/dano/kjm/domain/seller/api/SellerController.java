package com.dano.kjm.domain.seller.api;

import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.member.application.MemberService;
import com.dano.kjm.domain.member.dto.response.MemberDetail;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.seller.application.SellerService;
import com.dano.kjm.domain.seller.entity.Seller;
import com.dano.kjm.domain.seller.entity.SellerItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final MemberService memberService;

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

    @GetMapping("/{email}")
    public String main(@PathVariable String email, Model model) {
        MemberDetail member = memberService.findMember(email);
        Seller seller = sellerService.findByEmail(member.getEmail());
        model.addAttribute("member", member);
        model.addAttribute("seller", seller);
        return "seller/seller";
    }

    @GetMapping("/popup")
    public String popup(Model model) {
        model.addAttribute("item", new ItemAddDto());
        return "seller/sellPopup";
    }
}
