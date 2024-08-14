package com.ydp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    //发送人
    @Value("${spring.mail.username}")
    private String from;

    //接收人
    @Value("${spring.mail.jndiName}")
    private String to;

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

    /**
     * 发送html邮件
     *
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    /**
     * 发送附件邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filepath
     */
    public void sendAttachmentMail(String to, String subject, String content, String filepath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        FileSystemResource file = new FileSystemResource(new File(filepath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);
        helper.addAttachment(fileName + "_test.jpg", file); //发送多个附件

        mailSender.send(message);
    }

    /**
     * 发送图片邮件
     */
    public void sendImgMail( ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject("cxw");
        helper.setText("<html><head></head><body><h1>hello</h1><img src='cid:java'/><h1>hello another</h1><img src='cid:test'/></body></html>", true);
//        helper.setText(content, true);

//        System.out.println("图片2路径："+rscPath2);
        FileSystemResource res2 = new FileSystemResource(new File("/Users/new/Desktop/图片/004.jpeg"));
        helper.addInline("test", res2);

//        System.out.println("图片1路径："+rscPath);
        FileSystemResource res = new FileSystemResource(new File("/Users/new/Desktop/图片/java.png"));
        helper.addInline("java", res);


        mailSender.send(message);
    }

    public void sendImgMail2( String to, String subject, String content, String rscPath, String cid,String rscPath2,String cid2) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(cid, res);
        FileSystemResource res2 = new FileSystemResource(new File(rscPath2));
        helper.addInline(cid2, res2);
        mailSender.send(message);
    }


    //发送多个图片
    public void sendMultipleImageEmail() throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject("每日数据统计分析");//邮件主题

        //邮件内容(内嵌两张图片)
        helper.setText(
                "<html><head></head><body><h1>hello</h1><img src='cid:picture1'/><h1>hello another</h1><img src='cid:picture2'/></body></html>",
                true);
        //第一张图片
        FileSystemResource img1 = new FileSystemResource(new File("/Users/new/Desktop/图片/java.png"));
        helper.addInline("picture1", img1);

        //第二张图片
        FileSystemResource img2 = new FileSystemResource(new File("/Users/new/Desktop/图片/004.jpeg"));
        helper.addInline("picture2", img2);

        //发送邮件
        mailSender.send(msg);
    }

}
