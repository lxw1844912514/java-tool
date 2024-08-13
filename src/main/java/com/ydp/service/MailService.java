package com.ydp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    //发送人
    @Value("${spring.mail.username}")
    private String from;

    //注入
    @Autowired
    private JavaMailSender mailSender;

    public String sayHello() {
        return "Hello world";
    }

    /**
     * 发送文本邮件
     */
    public void sendTextMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        // 发送邮件
        mailSender.send(message);
    }
}
