package com.github.inza9hi.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class ValueConsumer implements Runnable {

  private LinkedBlockingQueue<String> queue;
  private CountDownLatch downLatch;


  public ValueConsumer(LinkedBlockingQueue<String> queue,CountDownLatch downLatch){
    this.queue = queue;
    this.downLatch = downLatch;
  }

  @Override
  public void run() {
    try {
      String key = queue.poll();
      while (key != null) {
        ReactorTest.getMono(key).subscribe();
        key = queue.poll();
      }
    }finally {
      downLatch.countDown();
    }

  }
}