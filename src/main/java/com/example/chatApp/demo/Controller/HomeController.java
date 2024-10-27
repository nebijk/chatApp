package com.example.chatApp.demo.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {



    @GetMapping("/")
    public String home(){
        return "welcome";
    }

    @GetMapping("/secured")
    public String secured(){
        return " secured place";
    }
}
