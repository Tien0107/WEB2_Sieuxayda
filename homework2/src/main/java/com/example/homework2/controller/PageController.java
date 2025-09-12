package com.example.homework2.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.homework2.model.Company;
import com.example.homework2.model.Staff;
import com.example.homework2.repository.CompanyRepository;
import com.example.homework2.repository.StaffRepository;


/**
 * Controller xử lý các trang hiển thị và thao tác CRUD cơ bản
 * Sử dụng form-based operations thay vì AJAX
 */

@Controller
public class PageController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StaffRepository staffRepository;

    /**
     * Trang chủ với thống kê
     */

    @GetMapping("/")
    public String index(Model model) {
        long totalCompanies = companyRepository.count();
        long totalStaffs = staffRepository.count();
        
        model.addAttribute("totalCompanies", totalCompanies);
        model.addAttribute("totalStaffs", totalStaffs);
        return "index";
    }

    /**
     * Hiển thị danh sách companies
     */
    @GetMapping("/companies")
    public String companies(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "companies";
    }

    /**
     * Hiển thị chi tiết company và danh sách staff
     */
    @GetMapping("/companies/{id}")
    public String companyDetail(@PathVariable("id") Long id, Model model) {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isEmpty()) {
            model.addAttribute("errorMessage", "Không tìm thấy công ty với ID: " + id);
            return "redirect:/companies";
        }
        
        Company company = companyOpt.get();
        List<Staff> staffs = staffRepository.findByCompanyId(id);
        
        model.addAttribute("company", company);
        model.addAttribute("staffs", staffs);
        return "company-detail";
    }

    /**
     * Hiển thị form thêm company
     */
    @GetMapping("/companies/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company-form";
    }

    /**
     * Xử lý thêm company mới
     */
    @PostMapping("/companies/add")
    public String addCompany(Company company, RedirectAttributes redirectAttributes) {
        try {
            companyRepository.save(company);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm công ty thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm công ty: " + e.getMessage());
        }
        return "redirect:/companies";
    }

    /**
     * Hiển thị form sửa company
     */
    @GetMapping("/companies/{id}/edit")
    public String editCompanyForm(@PathVariable("id") Long id, Model model) {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isEmpty()) {
            return "redirect:/companies";
        }
        model.addAttribute("company", companyOpt.get());
        return "company-form";
    }

    /**
     * Xử lý cập nhật company
     */
    @PostMapping("/companies/{id}/edit")
    public String updateCompany(@PathVariable("id") Long id, Company company, RedirectAttributes redirectAttributes) {
        try {
            company.setId(id);
            companyRepository.save(company);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật công ty thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật công ty: " + e.getMessage());
        }
        return "redirect:/companies";
    }

    /**
     * Xóa company
     */
    @GetMapping("/companies/{id}/delete")
    public String deleteCompany(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            companyRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa công ty thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa công ty: " + e.getMessage());
        }
        return "redirect:/companies";
    }

    /**
     * Hiển thị form thêm staff
     */
    @GetMapping("/companies/{companyId}/staff/add")
    public String addStaffForm(@PathVariable("companyId") Long companyId, Model model) {
        Optional<Company> companyOpt = companyRepository.findById(companyId);
        if (companyOpt.isEmpty()) {
            return "redirect:/companies";
        }
        
        Staff staff = new Staff();
        staff.setCompany(companyOpt.get());
        
        model.addAttribute("staff", staff);
        model.addAttribute("company", companyOpt.get());
        return "staff-form";
    }

    /**
     * Xử lý thêm staff mới
     */
    @PostMapping("/companies/{companyId}/staff/add")
    public String addStaff(@PathVariable("companyId") Long companyId, Staff staff, RedirectAttributes redirectAttributes) {
        try {
            Optional<Company> companyOpt = companyRepository.findById(companyId);
            if (companyOpt.isPresent()) {
                staff.setCompany(companyOpt.get());
                staffRepository.save(staff);
                redirectAttributes.addFlashAttribute("successMessage", "Thêm nhân viên thành công!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm nhân viên: " + e.getMessage());
        }
        return "redirect:/companies/" + companyId;
    }

    /**
     * Hiển thị form sửa staff
     */
    @GetMapping("/staff/{id}/edit")
    public String editStaffForm(@PathVariable("id") Long id, Model model) {
        Optional<Staff> staffOpt = staffRepository.findById(id);
        if (staffOpt.isEmpty()) {
            return "redirect:/companies";
        }
        
        Staff staff = staffOpt.get();
        model.addAttribute("staff", staff);
        model.addAttribute("company", staff.getCompany());
        return "staff-form";
    }

    /**
     * Xử lý cập nhật staff
     */
    @PostMapping("/staff/{id}/edit")
    public String updateStaff(@PathVariable("id") Long id, Staff staff, RedirectAttributes redirectAttributes) {
        try {
            Optional<Staff> existingStaffOpt = staffRepository.findById(id);
            if (existingStaffOpt.isPresent()) {
                Staff existingStaff = existingStaffOpt.get();
                staff.setId(id);
                staff.setCompany(existingStaff.getCompany());
                staffRepository.save(staff);
                redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhân viên thành công!");
                return "redirect:/companies/" + existingStaff.getCompany().getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật nhân viên: " + e.getMessage());
        }
        return "redirect:/companies";
    }

    /**
     * Xóa staff
     */
    @GetMapping("/staff/{id}/delete")
    public String deleteStaff(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Staff> staffOpt = staffRepository.findById(id);
            if (staffOpt.isPresent()) {
                Long companyId = staffOpt.get().getCompany().getId();
                staffRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công!");
                return "redirect:/companies/" + companyId;
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa nhân viên: " + e.getMessage());
        }
        return "redirect:/companies";
    }

}
