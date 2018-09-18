package com.springsecurity.lb.springsecuritydemo.controller;

import com.springsecurity.lb.springsecuritydemo.dto.User;
import com.springsecurity.lb.springsecuritydemo.exception.UseNotException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/user")
    public List<User> getUser(@RequestParam(required = false) String username){
        System.out.println(username);
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());

        return list;
    }

    //PathVariable 如果不设置初值的时候，{}中的名称要和想传入的名称相同
    @GetMapping("/user/{username}")
    public User User(@PathVariable String username){
        User user = new User();
        user.setUsename(username);
        return user;
    }

    @GetMapping("/testexcepton")
    public String getId(){
        throw  new RuntimeException("这是一个测试异常");
    }

    @GetMapping("/testUsenotException")
    public String testUsenotException(HttpServletRequest request){
        throw new UseNotException(request.getMethod());
    }


}
