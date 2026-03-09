package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spark/user/")
public class UserController {

    @Autowired
    private UserService userService;

    public String getUserPage(){
        User user = userService.getUserById("spark");

        return "user_list";
    }
}
