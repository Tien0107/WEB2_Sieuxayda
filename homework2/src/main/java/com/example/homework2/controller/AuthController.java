package com.example.homework2.controller;

import com.example.homework2.model.User;
import com.example.homework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller xử lý authentication (login/register)
 * Cung cấp các trang login, register và xử lý đăng ký
 */
@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Hiển thị trang login
     * @param error thông báo lỗi nếu có
     * @param logout thông báo đăng xuất thành công
     * @param model model để truyền dữ liệu cho view
     * @return tên template login
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        
        // Thêm thông báo lỗi nếu có
        if (error != null) {
            model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        
        // Thêm thông báo đăng xuất thành công
        if (logout != null) {
            model.addAttribute("successMessage", "Bạn đã đăng xuất thành công!");
        }
        
        return "login";
    }
    
    /**
     * Hiển thị trang đăng ký
     * @param model model để truyền dữ liệu cho view
     * @return tên template register
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    /**
     * Xử lý đăng ký user mới
     * @param user thông tin user từ form
     * @param model model để truyền dữ liệu cho view
     * @return redirect đến login nếu thành công, về register nếu có lỗi
     */
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            // Validate dữ liệu đầu vào
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                model.addAttribute("errorMessage", "Tên đăng nhập không được để trống!");
                return "register";
            }
            
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                model.addAttribute("errorMessage", "Mật khẩu không được để trống!");
                return "register";
            }
            
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                model.addAttribute("errorMessage", "Email không được để trống!");
                return "register";
            }
             user.setRole("ROLE_USER");
            // Đăng ký user
            userService.registerUser(user);
            model.addAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "login";
            
        } catch (RuntimeException e) {
            // Xử lý lỗi (username hoặc email đã tồn tại)
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
