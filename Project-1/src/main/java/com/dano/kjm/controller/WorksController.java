package com.dano.kjm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorksController {

    @GetMapping("/works")
    public String main() {
        return "works";
    }
}
