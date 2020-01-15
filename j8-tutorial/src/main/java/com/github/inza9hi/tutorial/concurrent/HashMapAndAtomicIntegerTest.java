package com.github.inza9hi.tutorial.concurrent;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapAndAtomicIntegerTest {

    static Map<Integer,AtomicInteger> map = Maps.newHashMap();
    static Integer it = 1000*1000*100;
    static Integer thread_num = 4;

    static final CountDownLatch latch = new CountDownLatch(thread_num);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < thread_num; i++) {
            map.put(i,new AtomicInteger(0));
        }
        for (int i = 0; i < thread_num; i++) {
            new Thread(new Add()).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+" final: ");
        for (Map.Entry<Integer, AtomicInteger> integerAtomicIntegerEntry : map.entrySet()) {
            System.out.println(integerAtomicIntegerEntry.getValue());
        }

    }




    static class Add implements Runnable{

        public void run() {
            for (int i = 0; i < it; i++) {
                int index = i%thread_num;
                final int res = map.get(index).addAndGet(1);
                if(res%1000==0){
                    System.out.println(Thread.currentThread().getName()+" now: "+res);
                }
            }
            latch.countDown();

        }
    }


}
