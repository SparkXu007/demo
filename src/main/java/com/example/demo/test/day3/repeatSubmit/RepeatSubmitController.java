package com.example.demo.test.day3.repeatSubmit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RepeatSubmitController {

    @RepeatSubmit(limitType = RepeatSubmit.Type.TOKEN, lockTime = 10L)
    @PostMapping(value = "/save")
    public String save(String name) {

        return "测试OK:" + name;
    }
}
