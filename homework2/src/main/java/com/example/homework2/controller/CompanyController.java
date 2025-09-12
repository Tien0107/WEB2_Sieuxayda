package com.example.homework2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homework2.model.Company;
import com.example.homework2.repository.CompanyRepository;

@RestController
@RequestMapping("/api/companies")
public class CompanyController extends AbstractCrudController<Company, Long> {

 public CompanyController(CompanyRepository repository) {
  super(repository);
 }
}
