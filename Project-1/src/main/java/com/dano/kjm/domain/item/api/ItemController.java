package com.dano.kjm.domain.item.api;

import com.dano.kjm.domain.item.application.ItemService;
import com.dano.kjm.domain.item.dto.request.ItemAddDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseBody
    public String addSellItem(@Valid @RequestBody ItemAddDto itemAddDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "seller/sellPopup";
        }

        try {

        } catch (Exception e) {
            log.error("error {}",e);
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
}
