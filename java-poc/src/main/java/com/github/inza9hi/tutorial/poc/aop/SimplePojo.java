package com.github.inza9hi.tutorial.poc.aop;

import org.springframework.aop.framework.AopContext;

/**
 * Created by lawulu on 15-11-24.
 */
public class SimplePojo implements Pojo {

    @Override
    public void foo() {
      //  System.out.println("in the begin of foo");
       // this.bar();
      //  System.out.println("===Another way====");
       ((Pojo) AopContext.currentProxy()).bar();
    }

    @Override
    public void bar(){
        System.out.println("in the bar");
    }

    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        pojo.foo();



    }
}
