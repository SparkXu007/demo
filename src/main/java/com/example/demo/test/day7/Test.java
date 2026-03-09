package com.example.demo.test.day7;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Test {

    public static void main(String[] args) {

//        testThreadLocal();

        testAtomicStampedReference();
    }

    /**
     * 测试使用Executors 创建固定大小线程池的方法
     * 注意：此方式创建的线程池使用无界阻塞队列，可能导致内存溢出（OOM）问题，生产环境不建议使用
     */
    public static void testExecuteService() {
        // 创建包含 5 个线程的固定大小线程池，底层使用 LinkedBlockingQueue 无界队列
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }

    /**
     * 测试使用 ThreadPoolExecutor 直接创建线程池的推荐方式
     * 通过显式指定线程池参数，可以更好地控制线程池行为，避免资源耗尽问题
     */
    public static void testThreadPoolExecutor() {
        // 创建 ThreadPoolExecutor，显式指定核心线程数、最大线程数、空闲时间、阻塞队列和拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("MyThreadPool-" + thread.getId());
                    return thread;
                },
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }

    // 测试ThreadLocal 用法
    public static void testThreadLocal() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        System.out.println(threadLocal.get());
        threadLocal.set(2);
        System.out.println(threadLocal.get());

        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set(3);
        System.out.println(threadLocal2.get());

        threadLocal.set(null);
        System.out.println(threadLocal.get());
        threadLocal.remove();
    }

    // 测试AtomicStampedReference 用法
    public static void testAtomicStampedReference() {
        AtomicStampedReference<Integer> atomicStampedReference =
                new AtomicStampedReference<>(100, 0);
        Integer reference = atomicStampedReference.getReference(); // 初始值
        int stamp = atomicStampedReference.getStamp();// 初始版本号
        atomicStampedReference.set(200, 1);
        atomicStampedReference.set(reference, 3);
        // 参数说明，参数1：期望值，参数2：更新值，参数3：期望版本号，参数4：更新版本号
        boolean b = atomicStampedReference.compareAndSet(reference, 99, stamp, 66);
        System.out.println(b);
        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }

}
