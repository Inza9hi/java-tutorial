package com.github.inza9hi.webflux;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
  @GetMapping("/hello/{latency}")
  public Mono<String> hello(@PathVariable int latency) {
    return Mono.just("Welcome to reactive world ~")
        .delayElement(Duration.ofMillis(latency)); //
//    return test(latency);

  }

  private Mono<String> test(int latency){

    try {
      Thread.sleep(latency);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Mono.just("Welcome to reactive world ~");
  }
}
