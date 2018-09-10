package com.springsecurity.lb.springsecuritydemo.controller;

import com.springsecurity.lb.springsecuritydemo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/user")
    public List<User> getUser(@RequestParam String username){
        System.out.println(username);
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());

        return list;
    }

    @GetMapping("/user/{usename}")
    public User User(@PathVariable String username){
        User user = new User();
        user.setUsename(username);
        return user;
    }
}
