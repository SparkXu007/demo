package com.example.demo.test.day7;

public interface B {

    void test();

    default void test1() {
        System.out.println("B");
    }
}
