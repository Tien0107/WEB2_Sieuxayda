package com.example.homework2.repository;

import com.example.homework2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository cho User entity
 * Cung cấp các method cơ bản và tìm kiếm theo username/email
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Tìm user theo username
     * @param username tên đăng nhập
     * @return Optional chứa User nếu tìm thấy
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Tìm user theo email
     * @param email địa chỉ email
     * @return Optional chứa User nếu tìm thấy
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Kiểm tra username đã tồn tại chưa
     * @param username tên đăng nhập cần kiểm tra
     * @return true nếu đã tồn tại
     */
    boolean existsByUsername(String username);
    
    /**
     * Kiểm tra email đã tồn tại chưa
     * @param email địa chỉ email cần kiểm tra
     * @return true nếu đã tồn tại
     */
    boolean existsByEmail(String email);
}
