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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {
  final static int count = 4;

  final static ExecutorService executorService = Executors.newFixedThreadPool(count);

  public static void main(String[] args) throws InterruptedException {
    final int nKeys = 200*1000;

    multiThread(nKeys);

    reactor(nKeys);
  }


  public static List<String> get(int nKeys){
    List<String> keys = Lists.newArrayList();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }
    return keys;
  }

  public static void reactor(int nKeys){

    final List<String> keys = get(nKeys);

    Stopwatch stopwatch = Stopwatch.createStarted();
    Flux.fromIterable(keys)
        .flatMap(x -> getMono(x))
//    .reduce(true, (x, y) -> x && y)
        .doFinally(x-> System.out.println("reactor Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)))
        .subscribeOn(Schedulers.fromExecutor(executorService)).subscribe();

  }


  public static void multiThread(int nKeys) throws InterruptedException {
    LinkedBlockingQueue<String> keys = Queues.newLinkedBlockingQueue();
    for (int i = 0; i < nKeys; i++) {
      keys.add("benchMark-"+i);
    }

    Stopwatch stopwatch = Stopwatch.createStarted();
    CountDownLatch downLatch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      ValueConsumer redisConsumer = new ValueConsumer(keys,downLatch);
      executorService.submit(redisConsumer);
    }
    downLatch.await();

    System.out.println("multiThread Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

  }



  public static Mono<String> getMono(String v){
    return Mono.just(v);
  }

}
