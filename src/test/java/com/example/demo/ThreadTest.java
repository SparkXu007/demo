package com.example.demo;

public class ThreadTest {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        Thread thread1 = new Thread(threadLocalTest);
        Thread thread2 = new Thread(threadLocalTest);
        thread1.start();
        try {
            Thread.sleep(10l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    static class ThreadLocalTest implements Runnable{

        @Override
        public void run() {
            Long aLong = threadLocal.get();
            if (aLong == null) {
                threadLocal.set(System.currentTimeMillis());
            }

            System.out.println(Thread.currentThread().getName()+"->"+threadLocal.get());
        }
    }
}
