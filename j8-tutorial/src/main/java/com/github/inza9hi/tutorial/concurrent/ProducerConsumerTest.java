package com.github.inza9hi.tutorial.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class ProducerConsumerTest {

    private static final ExecutorService TASK_POOL = Executors.newFixedThreadPool(8);

    private static final String POISON = "poison";


    static class MatchProducer implements Runnable {
        private List<String> bis;
        private BlockingQueue<String> queue;


        private MatchProducer(List<String> bis, BlockingQueue<String> queue) {
            this.bis = bis;
            this.queue = queue;
        }

        @Override
        public void run() {
            int i =0;
            while (true) {
                try {
                    String record = bis.get(i);
                    if (record != null) {
                        queue.put(record);
                        System.out.println(i);
                        i++;
                    }
                    //插入毒药，流程结束
                    if(i>=bis.size()){
                        queue.put(POISON);
                        System.out.println("sdf");
                        break;
                    }

                } catch (  InterruptedException e) {
                    e.printStackTrace();
                }

            }



        }

        private void start() {
            new Thread(this).start();
        }
    }

    static class MatchConsumer implements Runnable {
        private Long jobId;
        private Long crowdId;
        private BlockingQueue<String> queue;

        private MatchConsumer(BlockingQueue<String> queue, Long jobId, Long crowdId) {
            this.queue = queue;
            this.jobId = jobId;
            this.crowdId = crowdId;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String record = queue.take();
                    TASK_POOL.execute(new Runnable() {
                        @Override
                        public void run() {
                            notifyChannelWorker(record);
                        }
                    });
                    if (record.equals(POISON)) {
                        System.out.println("break");
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sdfss");
        }

        public void start() {
            new Thread(this).start();
        }



        public void notifyChannelWorker(String record) {
            System.out.println(Thread.currentThread().getName()+"sss"+record);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(100);
        List<String> bis = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            bis.add(""+i);
        }
        TASK_POOL.execute(()->{
            System.out.println("ss");
        });
        new MatchProducer(bis, queue).start();
        new MatchConsumer(queue, 10L, 11000L).start();
        TASK_POOL.awaitTermination(1, TimeUnit.DAYS);

    }

}