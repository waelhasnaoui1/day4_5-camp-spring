package com.sip.camp_spring_day_4_5.controllers;

import com.sip.camp_spring_day_4_5.entities.User;
import com.sip.camp_spring_day_4_5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addUser(){
        User user = new User();
        user.setPassword("12355353");
        user.setEmail("waelhasnaoui@gmail.com");
        user.setName("wael");
        user.setLastName("hasnaoui");
        return "user added";
    }
}
