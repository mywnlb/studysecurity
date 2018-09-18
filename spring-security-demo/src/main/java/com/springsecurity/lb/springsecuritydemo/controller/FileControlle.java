package com.springsecurity.lb.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController("/file")
public class FileControlle {
    @PostMapping("/upload")
    public String upload(MultipartFile file){
        return "success";
    }
}
