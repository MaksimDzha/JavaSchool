package ru.sbt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.String.format;

public class MultiThreadWork {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        ThreadPool threadPool = new ThreadPool(8);
        Compute compute = new Compute();

        long start = System.nanoTime();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> compute.doWork(j),
                            threadPool
                    ));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        System.out.println(format("Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000_000),
                value));

        threadPool.shutdown();
    }
}
