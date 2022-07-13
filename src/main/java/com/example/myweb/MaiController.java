package com.example.myweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaiController {
    @GetMapping("")
    public  String showHomePage(){

        return "index";
    }
}
