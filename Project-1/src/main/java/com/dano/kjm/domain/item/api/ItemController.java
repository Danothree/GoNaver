package com.dano.kjm.domain.item.api;

import com.dano.kjm.domain.item.application.ItemService;
import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import com.dano.kjm.domain.item.entity.ItemType;
import com.dano.kjm.global.config.security.SecurityMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String test(){
        return "items";
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @PostMapping("/popup")
    public String addSellItem(@Valid ItemAddDto itemAddDto, BindingResult bindingResult, Model model, @RequestParam MultipartFile itemImgFile) {

        if(bindingResult.hasErrors()) {
            return "item/itemPopup";
        }

        try {
            itemService.saveItem(itemAddDto, itemImgFile);
        } catch (Exception e) {
            log.error("error {}",e);
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/popup")
    public String popup(Model model, @AuthenticationPrincipal SecurityMember securityMember) {
//        (expression = "#this == 'anonymousUser' ? null : securityMember")
        ItemAddDto itemAddDto = new ItemAddDto();
        itemAddDto.setMemberId(securityMember.getId());
        model.addAttribute("itemAddDto", itemAddDto);
        return "item/itemPopup";
    }
}
