package com.example.myAirlineFlightservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ThymeleafController {
    
    @GetMapping("/")
    public String viewIndex() {
        return "index";
    }
}