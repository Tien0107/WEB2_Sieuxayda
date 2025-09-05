package com.example.homework2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/companies")
    public String companies() {
        return "companies";
    }

    @GetMapping("/companies/{id}")
    public String companyDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companyId", id);
        return "company-detail";
    }
}


