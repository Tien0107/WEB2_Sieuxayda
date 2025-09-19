package com.example.homework2.service;

import com.example.homework2.model.User;
import com.example.homework2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service xử lý logic nghiệp vụ cho User
 * Bao gồm đăng ký, tìm kiếm và mã hóa mật khẩu
 * Implement UserDetailsService để tích hợp với Spring Security
 */
@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Đăng ký user mới
     * @param user thông tin user cần đăng ký
     * @return User đã được lưu vào database
     * @throws RuntimeException nếu username hoặc email đã tồn tại
     */
    public User registerUser(User user) {
        // Kiểm tra username đã tồn tại
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }
        
        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        //  Thêm role mặc định khi đăng ký (USER thường)
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        
        return userRepository.save(user);

    }
    
    /**
     * Tìm user theo username
     * @param username tên đăng nhập
     * @return Optional chứa User nếu tìm thấy
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * Tìm user theo email
     * @param email địa chỉ email
     * @return Optional chứa User nếu tìm thấy
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Implement UserDetailsService.loadUserByUsername
     * Spring Security sẽ gọi method này để load user details khi đăng nhập
     * @param username tên đăng nhập
     * @return UserDetails object
     * @throws UsernameNotFoundException nếu không tìm thấy user
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy user với username: " + username);
        }
        return userOpt.get();
    }
}
