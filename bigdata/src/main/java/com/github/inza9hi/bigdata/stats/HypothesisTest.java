package com.github.inza9hi.bigdata.stats;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lawulu on 16-7-15.
 */
public class HypothesisTest {

    public static void main(String[] args) {


        LinkedList linkedList = new LinkedList();
        linkedList.add(0,"item");
        linkedList.add(3,"item");
        for (Object o : linkedList) {
            System.out.println(o);
        }

        Random r = new Random();
        int sizeA =1000;
        Integer[] as= new Integer[sizeA];
        for (int i = 0; i < sizeA; i++) {
            as[i]=r.nextInt(100);
        }


        Integer[] bs= new Integer[sizeA];
        for (int i = 0; i < sizeA; i++) {
            bs[i]=r.nextInt(150);
        }

        IntSummaryStatistics stats=Stream.of(bs).mapToInt(x->x).summaryStatistics();

        List<Integer> list = Lists.newArrayList();
        list.addAll(Arrays.asList(as));
        list.addAll(Arrays.asList(bs));

//        Collections.shuffle(list);
        //       Lists.partition(list,)

        System.out.println(stats);




    }


}
