package com.rahul.loginpage.Service;

public interface EmailService {
    void sendVerificationEmail(String to, String verificationToken);
}
