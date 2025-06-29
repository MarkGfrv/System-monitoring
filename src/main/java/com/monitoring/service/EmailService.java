package com.monitoring.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "email.queue")
    public void sendEmail(String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("email@example.com"); 
        email.setSubject("System Alert");
        email.setText(message);
        mailSender.send(email);
    }
}