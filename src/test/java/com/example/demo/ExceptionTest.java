package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExceptionTest {
    public static void main(String[] args) {
        int i = testFinally();
        System.out.println(i);
    }

    @Test
    public static int testFinally(){
        int i = 0;

        try {
            i = 1/0;
        }catch (Exception e){
            i = 5;
            return i;
        }
        finally {
            i = 3;
            return i;
        }
    }
}
