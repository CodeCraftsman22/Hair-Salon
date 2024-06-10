package com.example.Saloon.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class EmailSender {

    @Autowired
    private void sendEmail(String toEmail, String body) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("suchithraweb@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registered Successfully");

    }
}
