package com.github.inza9hi.flux;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


//https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html
public class FluxTest {



  public static void main(String[] args) throws IOException {
    debug();
  }

  static void create(){

    Flux.just("Hello", "World").subscribe(System.out::println);
    Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
    Flux.empty().subscribe(System.out::println);
    Flux.range(1, 10).subscribe(System.out::println);
    Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
    Flux.interval(Duration.ofMillis(500)).subscribe(System.out::println);

  }

  static void complexCreate(){

  Flux.generate(sink -> {
  sink.next("Hello");
  sink.complete();
  }).subscribe(System.out::println);

  final Random random = new Random();
  Flux.generate(ArrayList::new, (list, sink) -> {
    int value = random.nextInt(100);
    list.add(value);
    sink.next(value);
    if (list.size() == 10) {
    sink.complete();
    }
    return list;
  }).subscribe(System.out::println);

  }

  static void buffer(){
//    Flux

    Flux.range(1, 100).buffer(20).subscribe(System.out::println);
    Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
    Flux.interval(Duration.ofMillis(100)).take(2).toStream().forEach(System.out::println);
    Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
    Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);


  }

  static void window(){

    Flux.range(1, 100).window(20).subscribe(System.out::println);
  }

  static void subscribe(){

    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .subscribe(System.out::println, System.err::println);

    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .onErrorReturn(0)
        .subscribe(System.out::println);


    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalArgumentException()))

        .onErrorResume(e -> {
          if (e instanceof IllegalStateException) {
            return Mono.just(0);
          } else if (e instanceof IllegalArgumentException) {
            return Mono.just(-1);
          }
          return Mono.empty();
        })
        .subscribe(System.out::println);

  }

  static void schedule() throws IOException {

    Flux.create(sink -> {
      sink.next(Thread.currentThread().getName());
      sink.complete();
    })
        .publishOn(Schedulers.single())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .publishOn(Schedulers.elastic())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .subscribeOn(Schedulers.parallel())
        .subscribe(System.out::println);
//        .toStream()
//        .forEach(System.out::println);

    System.in.read();
  }

  static void debug(){
    Flux.range(1, 2).log("RangeTest").subscribe(System.out::println);

    Flux.just(1, 0).map(x -> 1 / x).checkpoint("test").subscribe(System.out::println);

  }
}
