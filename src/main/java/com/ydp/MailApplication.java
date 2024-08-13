package com.ydp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
//@EnableAutoConfiguration
@Slf4j
@SpringBootApplication
public class MailApplication
{

    @RequestMapping("/")
    public String hello(){
        log.info("信息日志");

        return "hello";
    }

    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        SpringApplication.run(MailApplication.class,args);
    }
}
