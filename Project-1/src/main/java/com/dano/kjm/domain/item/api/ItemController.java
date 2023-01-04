package com.dano.kjm.domain.item.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/items")
@Controller
public class ItemController {

    @GetMapping
    public String test(){
        return "items";
    }
}
