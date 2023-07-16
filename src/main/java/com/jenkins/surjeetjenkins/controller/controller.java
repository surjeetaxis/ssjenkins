package com.jenkins.surjeetjenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controller {
    @GetMapping(value = "/response")
    public String getResponse(){
        return "hello from spring boot";
    }
}
