package com.rahul.loginpage.Others;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGenerator {
    public static String generateVerificationToken() {
        // Generate a random UUID (Universally Unique Identifier) and return it as a string
        return UUID.randomUUID().toString();
    }
}
