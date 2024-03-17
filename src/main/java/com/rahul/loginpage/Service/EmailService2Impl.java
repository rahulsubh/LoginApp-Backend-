package com.rahul.loginpage.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService2Impl implements EmailService2{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Override
    public void sendVerificationEmail(String to,String token) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            // Set email properties
            helper.setFrom(env.getProperty("spring.mail.username"));
            helper.setTo(to);
            helper.setSubject("Password Reset");
            String emailContent = getEmailContent(token);
            helper.setText(emailContent, true);

            // Send email
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }

    }

    private String getEmailContent(String token) {
        return "Dear User,<br><br>"
                + "Please click on the following link to reset your Password:<br>"
                + "<a href='" + getVerificationLink(token) + "'>Reset your Password</a><br><br>"
                + "Thank you,<br>"
                + "Login App";
    }

    private String getVerificationLink(String token) {
        return "http://localhost:3000/resetpassword/"+token;
    }

}

