package com.rahul.loginpage.controller;

import com.rahul.loginpage.Service.EmailService2;
import com.rahul.loginpage.model.User;
import com.rahul.loginpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class ForgotPasswordController {

    @Autowired
    private EmailService2 emailService2;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestBody){
        String email = requestBody.get("email");
        User user = userRepository.findByEmail(email);

        if(user != null){
            String token = user.getVerificationToken();
            System.out.println(email);

            emailService2.sendVerificationEmail(email,token);
            return ResponseEntity.ok("Check your mail to rest the password!!");

        }


        return ResponseEntity.badRequest().body("Email Id does not exists");


    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody){
        String newPassword = requestBody.get("password");
        String token = requestBody.get("token");
        User user = userRepository.findByVerificationToken(token);
        user.setPassword(newPassword);
        userRepository.save(user);
        return ResponseEntity.ok("Password Changed Successfully");
    }
}
