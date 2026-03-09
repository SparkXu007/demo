package com.example.demo.test.day5;

import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        test1();
        test2();
        test3();
    }

    public static void test1() throws InterruptedException {

        A a = new A();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                a.increaseNum();
            }
        });
        t1.start();

        for (int i = 0; i < 10000000; i++) {
            a.increaseNum();
        }

        t1.join();

        System.out.println(a.getNum());
    }

    public static void test2() throws InterruptedException {

        A a = new A();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                a.increaseNum1();
            }
        });
        t1.start();

        for (int i = 0; i < 10000000; i++) {
            a.increaseNum1();
        }
        t1.join();

        System.out.println(a.getNum());
    }

    public static void test3() throws InterruptedException {

        A a = new A();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                a.increaseAge();
            }
        });
        t1.start();

        for (int i = 0; i < 10000000; i++) {
            a.increaseAge();
        }

        t1.join();

        System.out.println(a.getAge().get());
    }
}
