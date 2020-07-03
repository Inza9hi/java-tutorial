package com.github.inza9hi.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(FluxApplication.class, args);

//    GreetingWebClient gwc = new GreetingWebClient();
//    System.out.println(gwc.getResult());
  }
}
