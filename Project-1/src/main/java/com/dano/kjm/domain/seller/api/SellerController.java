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
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

    @GetMapping(value = {"/{email}","/{email}/{page}"})
    public String main(@PathVariable String email, Model model, @PathVariable Optional<Integer> page, HttpServletRequest request) {
        MemberDetail member = memberService.findMember(email);
        Seller seller = sellerService.findByEmail(member.getEmail());
        PageRequest pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<SellFormDto> items = sellerService.getItems(pageable);
        model.addAttribute("member", member);
        model.addAttribute("items", items);
        model.addAttribute("seller", seller);
        model.addAttribute("maxPage", 5);
        model.addAttribute("url", request.getRequestURL());
        return "seller/seller";
    }

    @GetMapping("/items/outer")
    public String allItems() {
        return "seller/outer";
    }
}
