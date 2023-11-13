package com.youcode.rentalhive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "<h1>Welcome to the Rental Hive API! created by YouCode students.</h1>";
    }
}
