package com.example.demo.test.day1;

public class SingleDemo {

    /**
     * 为啥要使用volatile
     *   在多线程环境下，如果不使用volatile，一个线程创建实例后，另一个线程可能无法立即看到该实例的更新
     *   例如，A线程创建实例后，B线程仍可能读取到null值
     */
    private static volatile SingleDemo singleDemo;

    private SingleDemo () {}

    public static SingleDemo getInstance() {

        // DCL(Double Check Lock 双重检查)
        if (null == singleDemo) {
            synchronized (SingleDemo.class) {
                if (null == singleDemo) {
                    /**
                     * singleDemo = new SingleDemo();
                     * 该行代码在底层分三步执行：1.分配内存（内存JVM） 2.初始化对象(内存JVN) 3.赋值引用(CPU)
                     * 问题描述：JVM和处理器（CPU）允许指令重排，可能导致执行步骤变成 1.分配内存 3.赋值引用 2.初始化对象
                     * 解决方案：使用volatile申明singleDemo。volatile插入内存屏障（memory Barrier），禁止指令重排，
                     *  即禁止 2.初始化对象和3.赋值引用之间的重排序，确保对象初始化后再赋值
                     */
                    singleDemo = new SingleDemo();
                }
            }
        }
        return singleDemo;
    }
}
