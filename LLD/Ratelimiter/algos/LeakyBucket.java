package LLD.Ratelimiter.algos;

import LLD.Ratelimiter.RateLimiter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Leaky Bucket: fixed-size queue; request allowed only if queue has space.
 * Good for: steady output rate, no bursts.
 */
public class LeakyBucket implements RateLimiter {
    private final BlockingQueue<Integer> queue;

    public LeakyBucket(int capacity) {
        queue = new LinkedBlockingDeque<>(capacity);
    }

    @Override
    public boolean grantAccess() {
        if (queue.remainingCapacity() > 0) {
            queue.add(1);
            return true;
        }
        return false;
    }
}
