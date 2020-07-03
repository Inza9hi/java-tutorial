package com.github.inza9hi.tutorial.poc.netty;

public class PromiseTest {

    public static void main(String[] args) throws InterruptedException {
        PromiseTest promiseTest = new PromiseTest();
        synchronized (promiseTest){
            promiseTest.wait();

        }
    }
}
