package com.github.inza9hi.redis;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class NormalRedisApplication  implements CommandLineRunner {

  @Autowired
  BenchmarkService benchmarkService;



  @Override
  public void run(String... args) throws Exception {

    final int nKeys = 200*1000;
    benchmarkService.singleThread(nKeys);
    benchmarkService.mulitThread(nKeys);
    benchmarkService.clean(nKeys);

  }

  public static void main(String[] args) {
    SpringApplication.run(NormalRedisApplication.class, args);

  }
}
