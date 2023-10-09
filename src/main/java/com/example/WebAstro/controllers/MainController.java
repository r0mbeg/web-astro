package com.example.WebAstro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String main(Model model){
        return "main";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }


    @GetMapping("/find")
    public String find(Model model) {
        return "find";
    }

}
