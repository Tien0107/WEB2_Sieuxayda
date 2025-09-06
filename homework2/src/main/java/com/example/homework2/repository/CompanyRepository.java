package com.example.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homework2.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
