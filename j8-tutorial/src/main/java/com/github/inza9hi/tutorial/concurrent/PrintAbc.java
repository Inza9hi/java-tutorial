package com.github.inza9hi.tutorial.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbc {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        Thread a = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();

                    conditionA.await();
                    System.out.println("A"+i);
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        });
         Thread b = new Thread(()->{
             for (int i = 0; i < 10; i++) {
                 try {
                     lock.lock();

                     conditionB.await();
                     System.out.println("B"+i);
                     conditionC.signal();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }finally {
                     lock.unlock();
                 }


             }

                });
         Thread c = new Thread(()->{
             for (int i = 0; i < 10; i++) {
                 try {
                     lock.lock();
                     conditionC.await();
                     System.out.println("C"+i);
                     conditionA.signal();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }finally {
                     lock.unlock();
                 }


             }
        });

         a.start();
         b.start();
         c.start();
         try {
             lock.lock();
             conditionA.signal();
         }finally {
             lock.unlock();
         }


    }
}
