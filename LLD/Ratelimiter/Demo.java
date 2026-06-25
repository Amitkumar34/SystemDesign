package LLD.Ratelimiter;

import LLD.Ratelimiter.algos.FixedWindow;
import LLD.Ratelimiter.userBuckets.UserFixedWindow;
import LLD.Ratelimiter.userBuckets.UserLeakyBucket;
import LLD.Ratelimiter.userBuckets.UserSlidingWindow;
import LLD.Ratelimiter.userBuckets.UserTokenBucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Demo: spawns concurrent threads to test per-user rate limiting. */
public class Demo {
    public static void main(String[] args) {
        int timeWindowInSeconds = 2;
        int capacity = 5;
        UserBuckets userBuckets =
//                new UserTokenBucket(capacity,3);
//                new UserLeakyBucket(capacity);
                new UserSlidingWindow(capacity, timeWindowInSeconds);
//                new UserFixedWindow(capacity, timeWindowInSeconds);

        System.out.println("Algorithm: " + userBuckets.getClass().getName());


        userBuckets.addUser(1);
        int noOfThreads = 12;
        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);
        for (int i = 0; i < noOfThreads; i++) {
            executorService.submit(() -> userBuckets.accessApp(1));
        }
        try {
            Thread.sleep(timeWindowInSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("------------------");
        for (int i = 0; i < noOfThreads; i++) {
            executorService.submit(() -> userBuckets.accessApp(1));
        }
        executorService.shutdown();
    }
}
