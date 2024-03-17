package com.rahul.loginpage.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Override
    public void sendVerificationEmail(String to, String verificationToken) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            // Set email properties
            helper.setFrom(env.getProperty("spring.mail.username"));
            helper.setTo(to);
            helper.setSubject("Verify Your Email Address");
            String emailContent = getEmailContent(verificationToken);
            helper.setText(emailContent, true);

            // Send email
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }

    }

    private String getEmailContent(String verificationToken) {
        // You can use an HTML template or simply concatenate strings for email content
        // Here's a simple example:
        return "Dear User,<br><br>"
                + "Please click on the following link to verify your email address:<br>"
                + "<a href='" + getVerificationLink(verificationToken) + "'>Verify Email</a><br><br>"
                + "Thank you,<br>"
                + "Login App";
    }

    private String getVerificationLink(String verificationToken) {
        // Construct the verification link based on your frontend URL and the token
        // Example: return "http://yourdomain.com/verify?token=" + verificationToken;
        return "http://localhost:3000/verify"; // Update with your actual frontend URL
    }
}
