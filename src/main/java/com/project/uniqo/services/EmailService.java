package com.project.uniqo.services;

import com.project.uniqo.models.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailService{

    @Autowired
    JavaMailSender emailSender;

    public void sendSimpleMessage(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ivarswing@gmail.com");
        message.setTo(contactForm.getTo());
        message.setSubject(contactForm.getSubject());
        message.setText(contactForm.getText());
        emailSender.send(message);
    }

}
