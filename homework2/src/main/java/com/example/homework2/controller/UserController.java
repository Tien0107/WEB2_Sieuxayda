package com.example.homework2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.homework2.model.User;
import com.example.homework2.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractCrudController<User, Long> {

 public UserController(UserRepository repository) {
  super(repository);
 }
}
