package com.github.inza9hi.flux;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class SampleSubscriber<T> extends BaseSubscriber<T> {

  public void hookOnSubscribe(Subscription subscription) {
    System.out.println("Subscribed");
    request(1);
  }

  @Override
  public void hookOnNext(T value) {
    System.out.println(value);
    request(1);
  }
  @Override
  protected void hookOnComplete() {
    System.out.println("complete");
  }

  public static void main(String[] args) {
    SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
    reactor.core.publisher.Flux<Integer> ints = reactor.core.publisher.Flux.just(1,2,3,4);

    ints.subscribe(ss);

  }
}