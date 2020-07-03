package com.github.inza9hi.redis;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BenchmarkService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  public void singleThread(int nKeys){
    List<String> keys = Lists.newArrayList();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }

    Stopwatch stopwatch = Stopwatch.createStarted();
    for (String key : keys) {
      stringRedisTemplate.opsForValue().set(key,key);
    }
    System.out.println("singleThread put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }

  public void mulitThread(int nKeys) throws InterruptedException {
    LinkedBlockingQueue<String> keys = Queues.newLinkedBlockingQueue();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }

    Stopwatch stopwatch = Stopwatch.createStarted();
    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    CountDownLatch downLatch = new CountDownLatch(4);
    for (int i = 0; i < 4; i++) {
      RedisConsumer redisConsumer = new RedisConsumer(keys,stringRedisTemplate,downLatch);
      executorService.submit(redisConsumer);
    }
    downLatch.await();

    System.out.println("mulitThread put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }

  public void clean(int nKeys){
    List<String> keys = Lists.newArrayList();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }

    Stopwatch stopwatch = Stopwatch.createStarted();
    for (String key : keys) {
      stringRedisTemplate.delete(key);
    }
    System.out.println("clean put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }



}
