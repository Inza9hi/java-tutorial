package com.github.inza9hi.tutorial.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ThreadDeadlock
 * <p/>
 * Task that deadlocks in a single-threaded Executor
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        public String call() throws Exception {
            // Here's where we would actually read the file
            Thread.sleep(1000*60);
            return "caall";
        }
    }

    public class RenderPageTask implements Callable<String> {
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }

        private String renderBody() throws InterruptedException {
            // Here's where we would actually render the page
            Thread.sleep(1000*60);

            return "";
        }
    }

    public static void main(String[] args) {
        try {
            ThreadDeadlock tdl = new ThreadDeadlock();
//            tdl.exec.submit(() -> System.out.println("11"));

            System.out.println(tdl.new RenderPageTask().call());

            tdl.exec.shutdown();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}