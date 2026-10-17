package LLD.RateLimiterV2;

import LLD.RateLimiterV2.enums.LimiterType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Demo: spawns concurrent threads to test per-user rate limiting. */
public class Demo {
    public static void main(String[] args) {
        int timeWindowInSeconds = 10;
        int capacity = 9;
        RateLimiterService rateLimiterService = new RateLimiterService(
                LimiterType.SLIDING_WINDOW, capacity, timeWindowInSeconds
        );

        int noOfThreads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);
        try {
            for (int i = 0; i < noOfThreads; i++) {
                executorService.submit(() -> rateLimiterService.allowRequest("1"));
                Thread.sleep( 500L);
            }
            executorService.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
