package com.ydp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class UploadFileController {

    @PostMapping("/upload")
    public String UploadFile(String name, MultipartFile photo, HttpServletRequest httpServletRequest) throws IOException {
        System.out.println(name);
//        System.out.println("字节：" + Arrays.toString(photo.getBytes()));
        System.out.println("名字：" + photo.getName());
        System.out.println("原始名字：" + photo.getOriginalFilename());
        System.out.println("大小：" + photo.getSize());
        System.out.println("类型：" + photo.getContentType());
        String Path = httpServletRequest.getServletContext().getRealPath("/upload/");
        System.out.println("路径：" + Path);

        saveFile(photo, Path);
        return "success";
    }

    public void saveFile(MultipartFile photo, String path) throws IOException {
        //判断存储的目录是否存在，如果不存在则创建
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("文件是否存在：" + dir.exists());

        File file = new File(path + photo.getOriginalFilename());
        System.out.println("完整路径：" + path + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
