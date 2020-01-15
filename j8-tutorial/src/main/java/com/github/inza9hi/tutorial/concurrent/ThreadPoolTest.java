package com.github.inza9hi.tutorial.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private static final int CORE_POOL_SIZE = 200;
    private static final int MAX_POOL_SIZE = 1000;
    private static final int KEEP_ALIVE_TIME = 5;
    private static final int QUEUE_SIZE = 100000;
    private static ExecutorService executor;

    static {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(QUEUE_SIZE));
    }

    public static void main(String[] args) {
//        AtomicInteger counter = new AtomicInteger();
//
//        executor.submit(() -> {
//            counter.set(1);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(counter.get());
//
//        });
//        executor.submit(() -> {
//            counter.compareAndSet(1, 2);
//            System.out.println(counter.get());
//        });
//
////        while (true) {
////            System.out.println(counter.get());
////        }
//
//        executor.shutdown();




    }
}
