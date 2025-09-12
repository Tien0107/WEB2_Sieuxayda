package com.example.homework2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.homework2.model.Company;
import com.example.homework2.model.Staff;
import com.example.homework2.repository.CompanyRepository;
import com.example.homework2.repository.StaffRepository;

@Controller
public class PageController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/companies")
    public String companies(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/{id}")
    public String companyDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companyId", id);
        List<Staff> staffs = staffRepository.findAll();
        model.addAttribute("staffs", staffs);
        return "company-detail";
    }
}
