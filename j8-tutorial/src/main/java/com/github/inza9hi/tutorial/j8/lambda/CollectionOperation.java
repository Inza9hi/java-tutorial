package com.github.inza9hi.tutorial.j8.lambda;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lawulu on 15-01-20.
 * @ see http://ifeve.com/lambda/
 */
public class CollectionOperation {

    public static List<String> names = new ArrayList<>();

    static {
       names.add("TaoBao");
       names.add("ZhiFuBao");

    }

    public static void main(String[] args) {
//        helloLambda();
//        useVariable();

        stream();
    }


    /**
     * Lambda其实就是一种创建SAM（Single Abstract Method）的语法糖，
     * 单参数可以省略输入
     * 但语句可以省略{}和return
     */
    public static void helloLambda(){



        List<String> collect = names.stream().map(String::toLowerCase)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);



    }

    /**
     * Strings.padEnd是指根据长度补齐String
     */

    public static void useVariable(){
        String[] array = {"a", "b", "c"};
        for(Integer i : Lists.newArrayList(1, 2, 3)){
            Stream.of(array).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
        }
    }

    public static void methodRef(){
        names.stream().map(name -> name.charAt(0)).collect(Collectors.toList());//无法方法引用

    }

    /**
     * 什么时候执行的system.out? 再每个元素被消费的时候打印自身!
     * 多个转换操作只会在汇聚操作的时候融合起来
     */
    public static void stream(){
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());//

    }
}
