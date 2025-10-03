package com.example.homework2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.homework2.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {}
