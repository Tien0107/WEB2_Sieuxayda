package com.example.homework2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.homework2.model.Member;
import com.example.homework2.repository.MemberRepository;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member";
    }

    @PostMapping("/members")
    public String addMember(Member member, Model model) {
        Member savedMember = memberRepository.save(member);
        model.addAttribute("addedMember", savedMember);
        return "add-member";
    }

    @GetMapping("/members/:id")
    public String showMemberDetails(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "member-details";
    }

} 