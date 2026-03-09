package com.example.demo;

public class SingletonTest {
    private volatile static SingletonTest singletonTest;

    public SingletonTest() {
    }

    public static SingletonTest getSingletonTest(){
        if (singletonTest == null) {
           synchronized (SingletonTest.class) {
               if (singletonTest == null) {
                   singletonTest = new SingletonTest();
               }
           }
        }

        return singletonTest;
    }
}
