package com.github.inza9hi.tutorial.concurrent.completable;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CompletableFutureTest {

  private static Random rand = new Random();
  private static long t = System.currentTimeMillis();
  static int getMoreData() {
    System.out.println("begin to start compute");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
    int a= rand.nextInt(1000);
    System.out.println("a:"+a);
    if(a%2==0){
      throw new RuntimeException("ss");
    }
    return a;
  }

  static String toStringTest(int t) {
    throw new RuntimeException("ss");

  }
  public static void main(String[] args) throws Exception {
    String name = null;


    testException(name);

    testExceptionMy();


    System.in.read();
  }

  static void testException(String name)throws Exception{


    CompletableFuture<String> completableFuture
        =  CompletableFuture.supplyAsync(() -> {
      if (name == null) {
        throw new RuntimeException("Computation error!");
      }
      return "Hello, " + name;
    }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
    System.out.println(completableFuture.get());

  }
  static void testExceptionMy()throws Exception{

    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);

    final CompletableFuture<String> stringCompletableFuture = future
        .thenApply(CompletableFutureTest::toStringTest)
        .handle((s, th) -> {
//          System.out.println(th.getMessage());
          return (s != null) ? s: "-1";});
    ;
//    Future<Integer> f = future.whenComplete((v, e) -> {
//      System.out.println(v);
//      System.out.println(e);
//    });
//
    System.out.println("111::"+stringCompletableFuture.join());
  }
}
