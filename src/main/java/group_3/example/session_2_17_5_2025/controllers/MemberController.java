package group_3.example.session_2_17_5_2025.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import group_3.example.session_2_17_5_2025.models.Member;

@Controller
public class MemberController {
 private ArrayList<Member> members = new ArrayList<>();

 public MemberController() {
  members.add(new Member(1, "Nguyen Mai Duc Tien", "ST22A", 24));
  members.add(new Member(2, "Pham Dong Chinh", "ST22A", 22));
  members.add(new Member(3, "Nguyen Van Thinh", "ST22A", 21));
 }

 @GetMapping("/")
 @ResponseBody
 public ArrayList<Member> sayHello() {
  return members;
 }

 @GetMapping("/user/{id}")
 @ResponseBody
 public Member getUser(@PathVariable int id) {
  Member member = members.get(id - 1);
  return member;
 }
}
