package com.example.simpleSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("login works");
        return "login";
    }
}