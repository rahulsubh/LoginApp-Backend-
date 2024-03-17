package com.rahul.loginpage.controller;

import com.rahul.loginpage.model.User;
import com.rahul.loginpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userControllerTest {
    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
}
