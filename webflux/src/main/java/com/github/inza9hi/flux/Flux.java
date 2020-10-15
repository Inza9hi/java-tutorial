package com.github.inza9hi.flux;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public abstract class Flux<T> implements Publisher<T> {
  public abstract void subscribe(Subscriber<? super T> s);

  public static <T> Flux<T> just(T... data) {
    return new FluxArray<>(data);
  }


}
