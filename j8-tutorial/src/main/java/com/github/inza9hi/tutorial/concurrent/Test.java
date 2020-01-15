package com.github.inza9hi.tutorial.concurrent;

import org.elasticsearch.common.unit.TimeValue;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) throws IOException {

        Supplier<String> supplier = String::new;
        String s = supplier.get();

        Runnable toRelease = () -> {
            while (true){

            }
        };
        new Thread(toRelease).start();
        System.in.read();
//        toRelease.start();
//        ExponentialBackoffIterator iterator = new ExponentialBackoffIterator(0,5);
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
    }


    private static class ExponentialBackoffIterator implements Iterator<TimeValue> {
        private final int numberOfElements;

        private final int start;

        private int currentlyConsumed;

        private ExponentialBackoffIterator(int start, int numberOfElements) {
            this.start = start;
            this.numberOfElements = numberOfElements;
        }

        @Override
        public boolean hasNext() {
            return currentlyConsumed < numberOfElements;
        }

        @Override
        public TimeValue next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Only up to " + numberOfElements + " elements");
            }
            int result = start + 10 * ((int) Math.exp(0.8d * (currentlyConsumed)) - 1);
            currentlyConsumed++;
            return TimeValue.timeValueMillis(result);
        }
    }
}
