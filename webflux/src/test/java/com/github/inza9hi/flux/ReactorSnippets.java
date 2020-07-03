package com.github.inza9hi.flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorSnippets {
  private static List<String> words = Arrays.asList(
      "the",
      "quick",
      "brown",
      "fox",
      "jumped",
      "over",
      "the",
      "lazy",
      "dog"
  );

  @Test
  public void simpleCreation() {
    Flux<String> fewWords = Flux.just("Hello", "World");
    Flux<String> manyWords = Flux.fromIterable(words);

    fewWords.subscribe(System.out::println);
    System.out.println();
    manyWords.subscribe(System.out::println);
  }

  @Test
  public void findingMissingLetter() {
    Flux<String> manyLetters = Flux
        .fromIterable(words)
        .flatMap(word -> Flux.fromArray(word.split("")))
        .distinct()
        .sort()
        .zipWith(Flux.range(1, Integer.MAX_VALUE),
            (string, count) -> String.format("%2d. %s", count, string));

    manyLetters.subscribe(System.out::println);
  }


  @Test
  public void restoringMissingLetter() {
    Mono<String> missing = Mono.just("s");
    Flux<String> fewWords = Flux.just("H", "W");

    Flux<String> allLetters = Flux
        .fromIterable(words)
        .flatMap(word -> Flux.fromArray(word.split("")))
        .concatWith(fewWords)
        .concatWith(missing)
        .distinct()
        .sort()
        .zipWith(Flux.range(1, Integer.MAX_VALUE),
            (string, count) -> String.format("%2d. %s", count, string));

    allLetters.subscribe(System.out::println);
  }

  @Test
  public void shortCircuit() {
    Flux<String> helloPauseWorld =
        Mono.just("Hello")
            .concatWith(Mono.just("world")
                .delaySubscription(Duration.ofMillis(500)));

    helloPauseWorld.subscribe(System.out::println);

    helloPauseWorld.toStream()
        .forEach(System.out::println);
  }
}


