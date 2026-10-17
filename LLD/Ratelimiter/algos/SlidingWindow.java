package LLD.Ratelimiter.algos;

import LLD.Ratelimiter.RateLimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Sliding Window: stores request timestamps; evicts entries older than window.
 * Good for: accurate rate limiting, no boundary burst issue.
 */
public class SlidingWindow implements RateLimiter {
    private final Queue<Long> queue;
    private final int timeWindowInSeconds;
    private final int capacity;

    public SlidingWindow(int capacity, int timeWindowInSeconds) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.capacity = capacity;
    }

    @Override
    public boolean grantAccess() {
        long curTime = System.currentTimeMillis();
        refreshQueue(curTime);
        if (queue.size() < capacity) {
            queue.offer(curTime);
            return true;
        }
        return false;
    }

    private void refreshQueue(long curTime) { // drop timestamps outside the window
        while (!queue.isEmpty() && (curTime - queue.peek()) / 1000 >= timeWindowInSeconds)
            queue.poll();
    }
}
