package com.github.inza9hi.tutorial.concurrent;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapTest {

//    static Map<Integer,Object> map = Maps.newConcurrentMap();
//    static Map<Integer,Object> map = Collections.synchronizedMap(new WeakHashMap<Integer, Object>());

//    static Map<Integer,Object> map = (new WeakHashMap<Integer, Object>());
    static final Cache<Integer, Object> map = CacheBuilder.newBuilder().weakValues().build();



    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);



        int max = 10000*10000;
        for (int i = 0; i < max; i++) {
            map.put(i,new Object());
            if(i%10000==0){
                System.out.println("add:"+i);
            }
            executorService.submit(new Remove(i));
        }
    }

    static class Remove implements Runnable{

        private int i;
        Remove(int i){
            this.i = i;
        }

        public void run() {

            if(i%10000==0){
                System.out.println("remove:"+i);
            }
            map.invalidate(i);
//            map.remove(i);

        }
    }
}


