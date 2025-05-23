package com.buoi1.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.buoi1.models.Member;
import org.springframework.ui.Model;


@Controller
public class MemberController {
 private ArrayList<Member> members = new ArrayList<>();

 public MemberController() {
  members.add(new Member(1, "Nguyen Mai Duc Tien", "ST22A", 24));
  members.add(new Member(2, "Pham Dong Chinh", "ST22A", 22));
  members.add(new Member(3, "Nguyen Van Thinh", "ST22A", 21));
 }

 @GetMapping("/members")
 public String showMemberList(Model model) {
  model.addAttribute("members", members);
  return "member"; // => member.html
 }

 @GetMapping("/user/{id}")
 public String getUser(@PathVariable int id, Model model) {
  Member member = members.stream()
          .filter(m -> m.getId() == id)
          .findFirst()
          .orElse(null);

  model.addAttribute("member", member);
  return "memberDetail"; // => memberDetail.html
 }
}


