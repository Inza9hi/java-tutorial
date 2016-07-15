package com.github.inza9hi.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by lawulu on 15-10-13.
 */
public class TimeWatch {

    long starts;

    private TimeWatch() {
        reset();
    }

    public static TimeWatch start() {
        return new TimeWatch();
    }

    public TimeWatch reset() {
        starts = System.nanoTime();
        return this;
    }

    public long time() {
        long ends = System.nanoTime();
        return ends - starts;
    }

    public long time(TimeUnit unit) {
        return unit.convert(time(), TimeUnit.NANOSECONDS);
    }

    public String toMinuteSeconds(){
        return String.format("%d min, %d sec", time(TimeUnit.MINUTES),
                time(TimeUnit.SECONDS) - time(TimeUnit.MINUTES));
    }

    public static void main(String[] args) throws InterruptedException {

        TimeWatch watch = TimeWatch.start();
        Thread.sleep(1000 * 10);
        System.out.println("Elapsed Time custom format: " + watch.toMinuteSeconds());
        System.out.println("Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS));
        System.out.println("Elapsed Time in nano seconds: " + watch.time());

        System.out.println("Elapsed Time in nano seconds: " + watch.time());

    }
}
