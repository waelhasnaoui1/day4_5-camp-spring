package com.sip.camp_spring_day_4_5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")

    public String home(){
        return "front/index.html";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "front/contact.html";
    }

}
