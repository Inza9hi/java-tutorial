package com.github.inza9hi.tutorial.j8.lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by lawulu on 15-11-20.
 */
public class StreamOperation {

    public static void main(String[] args) {
        reduce2();
    }

    /**
     * 什么时候执行的system.out? 在每个元素被消费的时候打印自身!
     * 多个转换操作只会在汇聚操作的时候融合起来
     */
    public static void stream(){
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());//

    }

    public static void reduce(){
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);

        BiConsumer<List<Integer>, List<Integer>> combiner
                =(list1,list2)->list1.addAll(list2);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        combiner);
        numsWithoutNull.forEach(System.out::println);

    }
    public static void reduce2(){
        List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
    }


}
