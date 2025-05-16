package com.buoi1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeamController {

    @GetMapping("/members")
    public String showMembers(Model model) {
        List<TeamMember> members = List.of(
                new TeamMember("Nguyễn Mai Đức Tiến"),
                new TeamMember("Nguyễn Văn Thịnh"),
                new TeamMember("Phạm Đông Chinh")
        );
        model.addAttribute("members", members);
        return "members";
    }
}
