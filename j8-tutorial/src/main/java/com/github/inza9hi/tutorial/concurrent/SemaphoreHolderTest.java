package com.github.inza9hi.tutorial.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by lawulu on 16-7-14.
 */
public class SemaphoreHolderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreHolderTest.class);

    public final static Semaphore semaphore = new Semaphore(1);


    public static void main(String[] args) {

        SemThread[] semThreads = new SemThread[5];
        for (int i = 0; i < 5; i++) {
            SemThread semThread = new SemThread("Thread"+i);
            semThreads[i]=semThread;
        }
        for (SemThread semThread:semThreads){
            new Thread(semThread).start();
        }

    }

    static class SemThread implements Runnable{

        private String name =null;

        SemThread(String name){
            this.name = name;
        }

        @Override
        public void run() {

                while (true){
                    LOGGER.info("into the thread for job name '{}'",name);
                    boolean lockFlag = false;
                    try {
                    if(!SemaphoreHolderTest.semaphore.tryAcquire(1,4L, TimeUnit.SECONDS)){

                        LOGGER.info("No available semaphore for job name '{}',for dept '{}'",name);
                        continue;
                    }
                        lockFlag =true;

                        LOGGER.info("begin for '{}'",name);
                        TimeUnit.SECONDS.sleep(5);
                        LOGGER.info("finish for '{}'", name);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        if(lockFlag) {
                            LOGGER.info("release for '{}'", name);
                            SemaphoreHolderTest.semaphore.release();
                        }
                    }

                }



        }
    }
}
