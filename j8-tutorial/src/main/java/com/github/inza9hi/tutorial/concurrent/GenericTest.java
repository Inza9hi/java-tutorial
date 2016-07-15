package com.github.inza9hi.tutorial.concurrent;

import java.util.List;

/**
 * Created by lawulu on 16-6-16.
 */
public  class GenericTest {

    public static <P extends GenericTest> GenericTest getPo(P po){
        return (GenericTest) new Object();
    }

    public static <P extends GenericTest> List<P> test(List<P> pos){

        for (P po : pos) {

         //   P coy = getPo(po);

        }

        return null;

    }
}
