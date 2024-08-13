package com.ydp.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {
    public String sayHello() {
//        System.out.println("Hello world");
        return "Hello world";
    }

    /**
     * 发送文本邮件
     */
    public void sendTextMail(String to, String subject, String content) {
//        new SimpleMail();
    }
}
