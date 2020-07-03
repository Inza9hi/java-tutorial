package com.github.inza9hi.tutorial.algorithm.lru;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LinkedHashMapTest {

  public static void main(String[] args) {

    int times = 100*1000;

    final Map<String, String> hashMap = new HashMap<String, String>(times,0.75f);
    final Map<String, String> linkedHashMap = new LinkedHashMap<String, String>(times,0.75f,false);

    Stopwatch stopwatch = Stopwatch.createStarted();



    stopwatch.reset();
    stopwatch.start();
    for (int i = 0; i <times ; i++) {
      linkedHashMap.put(""+i,""+i);
    }
    stopwatch.stop(); // optional
    System.out.println("linkedHashMap put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));





    stopwatch.reset();
    stopwatch.start();
    for (int i = 0; i <times ; i++) {
      linkedHashMap.get(""+i);
    }

    stopwatch.stop(); // optional
    System.out.println("linkedHashMap get Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));



//
//        stopwatch.reset();
//    stopwatch.start();
//    for (int i = 0; i <times ; i++) {
//      hashMap.put(""+i,""+i);
//    }
//    stopwatch.stop(); // optional
//    System.out.println("hashMap put Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));
//
//    stopwatch.reset();
//    stopwatch.start();
//    for (int i = 0; i <times ; i++) {
//      hashMap.get(""+i);
//    }
//    stopwatch.stop(); // optional
//    System.out.println("hashMap get Time elapsed: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));



  }

}
