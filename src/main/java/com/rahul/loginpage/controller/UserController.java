package com.rahul.loginpage.controller;

import com.rahul.loginpage.Others.TokenGenerator;
import com.rahul.loginpage.Service.EmailService;
import com.rahul.loginpage.model.Associate;
import com.rahul.loginpage.model.User;
import com.rahul.loginpage.repository.AssociateRepository;
import com.rahul.loginpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AssociateRepository associateRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        String str = "\"" + user.getAssociateId() + "\"";
        Associate existingUser = associateRepository.findByAssociateIds(str);

//        System.out.println(associateRepository.findByAssociateIds("\"2136470\""));
//        System.out.println(associateRepository.findByAssociateIds("1234567"));
//        if (existingUser != null && existingUser.getAssociateIds().equals(str))
        System.out.println(existingUser);
        System.out.println(str);
        //        System.out.println(userRepository.findByAssociateId("2136444"));
        //            System.out.println(existingUser.getAssociateId());
        //        System.out.println("Hello2");
        if (existingUser != null) {
            User user1 = userRepository.findByAssociateId(user.getAssociateId());
            if (user1 != null) {
                return ResponseEntity.ok("Already Registered with this associate ID.");
            }if (existingUser.getAssociateIds().equals(str)){
                if(userRepository.findByEmail(user.getEmail()) != null){
                    return ResponseEntity.badRequest().body("Email Id exists in our records");
                }
                user.setVerificationToken(TokenGenerator.generateVerificationToken());
                // Save user details
                userRepository.save(user);

                // Send verification email
                emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());

                return ResponseEntity.ok("Registration successful. Please check your email for verification.");
            }
        }

        return ResponseEntity.badRequest().body("Associate ID is not in our records.");



    }

    @PostMapping("/operation")
    public ResponseEntity<String> performOperation(@RequestBody Map<String,String> requestBody) {
        String id = requestBody.get("username");
        User user = userRepository.findByAssociateId(id);
        if(user != null && requestBody.get("password").equals(user.getPassword())){
            return ResponseEntity.ok("Login Successfully");
        }else{
            return ResponseEntity.badRequest().body("Login Failed");
        }

    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/associates")
    public List<Associate> getAllAssociates(){
        return associateRepository.findAll();
    }

//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyUser(@RequestBody Map<String,String> requestBody){
//
//        String t =  requestBody.get("token");
//
//        User user =  userRepository.findByVerificationToken(t);
//
//        String id = user.getAssociateId();
//
//        System.out.println(id);
//
//        return ResponseEntity.ok("Verification Completed for " + id);
//
//    }
}



