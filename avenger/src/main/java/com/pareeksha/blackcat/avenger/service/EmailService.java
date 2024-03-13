package com.pareeksha.blackcat.avenger.service;

import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Service
@Component
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom("bibhu.bhushan0403@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body); // true indicates HTML content
            javaMailSender.send(message);
        }
        catch (Exception e){
            log.info("Exception occured while sending mail " ,e);
        }
        log.info("Mail sent successfully! ");
    }
}
