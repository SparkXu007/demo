package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testForEach(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五");
        
        list.stream().forEach((String str) -> {
            System.out.println("str = " + str);
        });

        System.out.println("--------------");
        list.stream().forEach(str -> System.out.println("str = " + str));
    }
    
    @Test
    public void testCount(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五");

        long count = list.stream().count();
        System.out.println("count = " + count);
    }
}
