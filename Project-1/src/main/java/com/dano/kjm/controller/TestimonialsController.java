package com.dano.kjm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestimonialsController {

    @GetMapping("/testimonials")
    public String main() {
        return "testimonials";
    }
}
