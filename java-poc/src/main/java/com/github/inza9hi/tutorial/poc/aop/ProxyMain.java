package com.github.inza9hi.tutorial.poc.aop;

import org.springframework.aop.framework.ProxyFactory;


/**
 * Created by lawulu on 15-11-24.
 * @see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html#aop-understanding-aop-proxies
 *
 */
public class ProxyMain {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());
        factory.setExposeProxy(true);

        Pojo pojo = (Pojo) factory.getProxy();

        // this is a method call on the proxy!
        pojo.foo();

        System.out.println("=============== ");


        pojo.bar();
    }


}
