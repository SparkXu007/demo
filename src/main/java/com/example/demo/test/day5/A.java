package com.example.demo.test.day5;

import java.util.concurrent.atomic.AtomicInteger;

public class A {

    private int num;

    private AtomicInteger age = new AtomicInteger();

    public int getNum() {

        return num;
    }

    public void increaseNum() {
        num++;
    }

    public void increaseNum1() {
        synchronized (this) {
            num++;
        }
    }

    public AtomicInteger getAge() {
        return age;
    }

    public void increaseAge() {
        age.incrementAndGet();
    }
}
