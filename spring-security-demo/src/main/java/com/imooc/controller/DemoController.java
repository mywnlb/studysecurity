package com.imooc.controller;

import com.imooc.dto.User;
import com.imooc.exception.UseNotException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/user")
    public List<User> getUser(@RequestParam(required = false) String username){
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());

        return list;
    }

    @GetMapping("/user/me")
    public Object getUserMe(Authentication authentication){
        return authentication;
    }

    @GetMapping("/user/details")
    public Object getUserDetails(@AuthenticationPrincipal UserDetails authentication){
        return authentication;
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
