package com.example.homework2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homework2.model.Staff;

/**
 * Repository cho Staff entity
 * Cung cấp các method cơ bản và tìm kiếm theo company
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {
    
    /**
     * Tìm tất cả staff theo company id
     * @param companyId id của company
     * @return danh sách staff thuộc company đó
     */
    List<Staff> findByCompanyId(Long companyId);
}

