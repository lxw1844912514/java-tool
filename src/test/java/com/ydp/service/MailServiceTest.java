package com.ydp.service;

import com.ydp.MailApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@RunW
@SpringBootTest(classes = {MailApplication.class})
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Test
    public void sayHelloTest(){
        System.out.println(mailService.sayHello());
    }
}
