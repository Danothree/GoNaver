package com.dano.kjm.domain.seller.api;

import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.Item;
import com.dano.kjm.domain.member.application.MemberService;
import com.dano.kjm.domain.member.dto.response.MemberDetail;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.seller.application.SellerService;
import com.dano.kjm.domain.seller.dto.SellFormDto;
import com.dano.kjm.domain.seller.entity.Seller;
import com.dano.kjm.domain.seller.entity.SellerItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String main(@PathVariable String email, Model model, Optional<Integer> page) {
        MemberDetail member = memberService.findMember(email);
        Seller seller = sellerService.findByEmail(member.getEmail());
        PageRequest pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<SellFormDto> items = sellerService.getItems(seller.getId(), pageable);
        model.addAttribute("member", member);
        model.addAttribute("items", items);
        model.addAttribute("seller", seller);
        model.addAttribute("maxPage", 5);
        return "seller/seller";
    }

}
