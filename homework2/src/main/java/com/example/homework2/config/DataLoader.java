package com.example.homework2.config;

import com.example.homework2.model.Company;
import com.example.homework2.model.Staff;
import com.example.homework2.model.User;
import com.example.homework2.repository.CompanyRepository;
import com.example.homework2.repository.StaffRepository;
import com.example.homework2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DataLoader - Tạo dữ liệu mẫu khi ứng dụng sẵn sàng
 * Sử dụng ApplicationReadyEvent để đảm bảo tất cả beans đã được khởi tạo
 */
@Component
public class DataLoader {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // Chỉ tạo dữ liệu mẫu nếu chưa có user nào
        if (userRepository.count() == 0) {
            createSampleUsers();
            createSampleCompanies();
            createSampleStaffs();
        }
    }
    
    /**
     * Tạo user mẫu
     */
    private void createSampleUsers() {
        // Tạo admin user
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setEmail("admin@company.com");
        admin.setFullName("Administrator");
        userRepository.save(admin);
        
        // Tạo user thường
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEmail("user@company.com");
        user.setFullName("Người dùng");
        userRepository.save(user);
        
        System.out.println("Đã tạo " + userRepository.count() + " user mẫu");
    }
    
    /**
     * Tạo company mẫu
     */
    private void createSampleCompanies() {
        Company company1 = new Company();
        company1.setName("Công ty TNHH ABC");
        company1.setAddress("123 Đường ABC, Quận 1, TP.HCM");
        company1.setPhone("028-1234-5678");
        company1.setEmail("contact@abc.com");
        company1.setWebsite("https://www.abc.com");
        companyRepository.save(company1);
        
        Company company2 = new Company();
        company2.setName("Công ty Cổ phần XYZ");
        company2.setAddress("456 Đường XYZ, Quận 2, TP.HCM");
        company2.setPhone("028-8765-4321");
        company2.setEmail("info@xyz.com");
        company2.setWebsite("https://www.xyz.com");
        companyRepository.save(company2);
        
        System.out.println("Đã tạo " + companyRepository.count() + " company mẫu");
    }
    
    /**
     * Tạo staff mẫu
     */
    private void createSampleStaffs() {
        // Lấy company đầu tiên
        Company company1 = companyRepository.findAll().get(0);
        
        Staff staff1 = new Staff();
        staff1.setName("Nguyễn Văn An");
        staff1.setPosition("Giám đốc");
        staff1.setEmail("an.nguyen@abc.com");
        staff1.setPhone("0901-234-567");
        staff1.setCompany(company1);
        staffRepository.save(staff1);
        
        Staff staff2 = new Staff();
        staff2.setName("Trần Thị Bình");
        staff2.setPosition("Trưởng phòng IT");
        staff2.setEmail("binh.tran@abc.com");
        staff2.setPhone("0902-345-678");
        staff2.setCompany(company1);
        staffRepository.save(staff2);
        
        // Lấy company thứ hai
        if (companyRepository.count() > 1) {
            Company company2 = companyRepository.findAll().get(1);
            
            Staff staff3 = new Staff();
            staff3.setName("Lê Văn Cường");
            staff3.setPosition("Nhân viên Marketing");
            staff3.setEmail("cuong.le@xyz.com");
            staff3.setPhone("0903-456-789");
            staff3.setCompany(company2);
            staffRepository.save(staff3);
        }
        
        System.out.println("Đã tạo " + staffRepository.count() + " staff mẫu");
    }
}
