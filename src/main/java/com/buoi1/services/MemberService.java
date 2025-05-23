// package com.buoi1.services;

// import com.buoi1.models.Member;

// import java.util.*;

// public class MemberService {

//     // Fake database
//     private final Map<String, Member> memberMap = new HashMap<>();

//     public MemberService() {
//         // Tạo sẵn 2 user
//         memberMap.put("1", new Member("1", "Hải Nam", "22", "Nam", new Date()));
//         memberMap.put("2", new Member("2", "Linh", "23", "Nữ", new Date()));
//     }

//     public Member getMemberById(String id) {
//         return memberMap.get(id); // trả về null nếu không tìm thấy
//     }

//     public List<Member> getAllMembers() {
//         return new ArrayList<>(memberMap.values());
//     }
// }
