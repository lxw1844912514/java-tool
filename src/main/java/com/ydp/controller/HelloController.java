package com.ydp.controller;

import com.ydp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap map) {
        map.addAttribute("name", "lxw");
        return "hello";
    }

    @GetMapping("/test")
    public String test(String name, Integer phone) {
        return "ok" + name + phone;
    }

    @GetMapping("/getTest1")
//    url:http://localhost:8009/getTest1?nickname=zhangsan
//    RequestParam : 接收传进来的nickname， 绑定到name
//    required=false 非必传 ，defaultValue 默认值,在没有接收到nickname 的传值时返回默认值
    public String getTest(@RequestParam(value = "nickname", required = false, defaultValue = "test") String name, Integer phone) {
        return "ok,姓名：" + name + ",手机号：" + phone;
    }

    @PostMapping("/postTest1")
    public String postTest1(String name, String pwd) {
        return "姓名:" + name + ", 密码：" + pwd;
    }

    @PostMapping("/postTest2")
    //form-data 传输
    public String postTest2(User user) {
        System.out.println(user);
        return user.toString();
    }

    @PostMapping("/postTest3")
    // 接受json格式的传值
    public String postTest3(@RequestBody User user) {
        return user.toString();
    }

}
