package com.example.homework2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.homework2.model.Staff;

public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findByCompanyId(String companyId);
}

