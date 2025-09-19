package com.example.homework2.controller;

import com.example.homework2.model.User;
import com.example.homework2.repository.UserRepository;

public class UserController extends AbstractCrudController<User, Long> {

    public UserController(UserRepository repository) {
        super(repository);
        }
}
