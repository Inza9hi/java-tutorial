package com.github.inza9hi.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by lawulu on 16-7-4.
 * @see https://www.quora.com/How-do-we-calculate-the-size-of-the-object-in-Java
 * @see http://jiangbo.me/blog/2012/02/21/java-lang-instrument/
 * 可以计算某个对象的大小
 */
public class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }

}
