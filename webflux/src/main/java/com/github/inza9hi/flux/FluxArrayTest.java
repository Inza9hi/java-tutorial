package com.github.inza9hi.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxArrayTest {

  public static void main(String[] args) {
    Flux.just(1, 2, 3, 4, 5).subscribe(new Subscriber<Integer>() { // 1

      Subscription s;
      boolean complete ;

      @Override
      public void onSubscribe(Subscription s) {
        this.s = s;
        System.out.println("onSubscribe");
        s.request(1);   // 2
      }

      @Override
      public void onNext(Integer integer) {
        System.out.println("onNext:" + integer);
        if(!complete){
          s.request(1);

        }

      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onComplete() {
        this.complete = true;
        System.out.println("onComplete");
      }
    });
  }

}
