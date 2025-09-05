package com.example.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homework2.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
 
}