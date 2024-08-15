package com.ydp.service;

import com.ydp.MailApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.filter.RequestContextFilter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletRequest;
import java.util.Objects;


//@RunW
@SpringBootTest(classes = {MailApplication.class})
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;
    @Qualifier("httpServletRequest")
    @Autowired
    private ServletRequest httpServletRequest;
    @Autowired
    private RequestContextFilter requestContextFilter;

    @Test
    public void sayHelloTest() {
        System.out.println(mailService.sayHello());
    }

    /**
     * 发送文本邮件
     */
    @Test
    public void sendTextMailTest() {
        mailService.sendTextMail("1844912514@qq.com", "Java发送邮件", "这是第一封邮件内容");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color:#d0e4fe;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color:orange;\n" +
                "            text-align:center;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-family:\"Times New Roman\";\n" +
                "            font-size:20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h3 style=\"height: min-content\"> 这里html内容</h3>\n" +
                "<p style=\"color: blueviolet\"> 这是段落</p>\n" +
                "<div>\n" +
                "    <table>\n" +
                "        <thead>\n" +
                "        <th>\n" +
                "            <td>标题1</td>\n" +
                "            <td>标题2</td>\n" +
                "            <td>标题3</td>\n" +
                "        </th>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "        <tr style=\"\">\n" +
                "            <td>内容1</td>\n" +
                "            <td>内容1</td>\n" +
                "            <td>内容1</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>内容2</td>\n" +
                "            <td>内容3</td>\n" +
                "            <td>内容3</td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1844912514@qq.com", "Java发送html邮件", content);
        System.out.println("发送成功");
    }

    @Test
    public void sendAttachmentMail() throws MessagingException {
        String filePath = "/Users/new/Desktop/图片/004.jpeg";
        mailService.sendAttachmentMail("1844912514@qq.com", "Java发送附件邮件", "这是带附件的邮件内容", filePath);
        System.out.println("发送成功");
    }

    @Test
    public void sendImgMail() throws MessagingException {
        String rscId = "java";
        String imgPath = "/Users/new/Desktop/图片/java.png";
        String rscId2 = "test";
        String imgPath2 = "/Users/new/Desktop/图片/004.jpeg";
        //  注意这里的cid 是contentid 的意思，固定的变量名称
        String content = "<html><head></head><body><h1>hello</h1><img src='cid:" + rscId + "'/><h1>hello another</h1><img src='cid:" + rscId2 + "'/></body></html>";
//        String content = "<html><head></head><body><h1>hello</h1><img src='cid:java'/><h1>hello another</h1><img src='cid:test'/></body></html>";
        mailService.sendImgMail2("1844912514@qq.com", "Java发送图片邮件123", content, imgPath, rscId, imgPath2, rscId2);
        System.out.println("发送成功");

    }

    @Test
    public void sendImageTest() throws MessagingException {
        mailService.sendImgMail();
    }


    @Test
    public void sendMultipleImageEmailTest() throws MessagingException {
        mailService.sendMultipleImageEmail();
    }

    // 发送模版邮件
    @Test
    public void sendTemplateTest() throws MessagingException {
        Context context =new Context();
        context.setVariable("id","001"); //设置变量的值

        String emailContent=templateEngine.process("emailTemplate",context); //将变量带入模版文件中
        mailService.sendHtmlMail("1844912514@qq.com","这是模版邮件",emailContent);

    }

    @Test
    public void getResourceTest(){
//        String resourcePath = Objects.requireNonNull(MailServiceTest.class.getClassLoader().getResource("qrcode/1.html")).getPath();
//        System.out.println(resourcePath);

//        String resourcePath2 = Objects.requireNonNull(MailServiceTest.class.getResource("qrcode/2.html")).getPath();
//        System.out.println(resourcePath2);

        String imgPath = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("static/qrcode/2.html")).getPath();
        System.out.println("图片路径：" + imgPath);

//        String resourcePath3 = servletContext.getRealPath("/static/resource.txt");
//        System.out.println(resourcePath3);
    }


    @Test
    public void pathTest(){
        String path=httpServletRequest.getServerName();
        System.out.println(path);
        String path3= String.valueOf(httpServletRequest.getLocalPort());
        System.out.println(path3);

        String path2= requestContextFilter.toString();
        System.out.println(path2);
    }
}
