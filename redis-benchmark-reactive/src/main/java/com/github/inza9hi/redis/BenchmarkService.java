package com.github.inza9hi.redis;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class BenchmarkService {

  final static int count = 4;

  final static ExecutorService executorService = Executors.newFixedThreadPool(count);


  @Autowired
  private ReactiveStringRedisTemplate stringRedisTemplate;

  public void reactor(int nKeys){
    LinkedBlockingQueue<String> keys = initKeys(nKeys);

    Stopwatch stopwatch = Stopwatch.createStarted();
     Flux.fromIterable(keys)
        .flatMap(x -> stringRedisTemplate.opsForValue().set(x, x))
//    .reduce(true, (x, y) -> x && y)
         .doFinally(x-> System.out.println("reactor Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)))
         .subscribeOn(Schedulers.fromExecutor(executorService)).subscribe();

  }

  private LinkedBlockingQueue<String> initKeys(int nKeys) {
    LinkedBlockingQueue<String> keys = Queues.newLinkedBlockingQueue();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }
    return keys;
  }

  public void multiThread(int nKeys) throws InterruptedException {
    LinkedBlockingQueue<String> keys = initKeys(nKeys);

    Stopwatch stopwatch = Stopwatch.createStarted();
    CountDownLatch downLatch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      RedisConsumer redisConsumer = new RedisConsumer(keys,stringRedisTemplate,downLatch);
      executorService.submit(redisConsumer);
    }
    downLatch.await();

    System.out.println("multiThread Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }

  public void singleThread(int nKeys){

  }

  public void clean(int nKeys){
    LinkedBlockingQueue<String> keys = initKeys(nKeys);

    Stopwatch stopwatch = Stopwatch.createStarted();
    for (String key : keys) {
      stringRedisTemplate.delete(key).subscribe();
    }
    System.out.println("clean put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }



}
