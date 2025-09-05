package com.example.homework2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homework2.model.Staff;
import com.example.homework2.repository.StaffRepository;

@RestController
@RequestMapping("/api/staff")
public class StaffController extends AbstractCrudController<Staff, Long> {

 public StaffController(StaffRepository repository) {
  super(repository);
 }
}
