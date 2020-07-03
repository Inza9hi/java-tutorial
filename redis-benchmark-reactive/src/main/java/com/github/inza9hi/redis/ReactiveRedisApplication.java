package com.github.inza9hi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class ReactiveRedisApplication implements CommandLineRunner {

  @Autowired
  BenchmarkService benchmarkService;



  @Override
  public void run(String... args) throws Exception {

    final int nKeys = 200*1000;
//    final int nKeys = 100;
    benchmarkService.clean(nKeys);
    benchmarkService.multiThread(nKeys);

    benchmarkService.clean(nKeys);
//
    benchmarkService.reactor(nKeys);


  }

  public static void main(String[] args) {
    SpringApplication.run(ReactiveRedisApplication.class, args);

  }
}
