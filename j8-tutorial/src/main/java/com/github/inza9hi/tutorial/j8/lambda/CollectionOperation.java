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
        helloLambda();
        useVariable();

        methodRef();;//

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

        //i 如果不是final,就会报错
//        for(int i = 1; i<4; i++){
//            Stream.of(array).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
//        }
    }

//    objectName::instanceMethod
//    ClassName::staticMethod
//    ClassName::instanceMethod
//    前两种方式类似，等同于把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来调用。比如System.out::println等同于x->System.out.println(x)；Math::max等同于(x, y)->Math.max(x,y)。
//
//    最后一种方式，等同于把lambda表达式的第一个参数当成instanceMethod的目标对象，其他剩余参数当成该方法的参数。比如String::toLowerCase等同于x->x.toLowerCase()。

    public static void methodRef(){
        names.stream().map(name -> name.charAt(0)).collect(Collectors.toList()).forEach(System.out::println);//无法方法引用

    }



}
