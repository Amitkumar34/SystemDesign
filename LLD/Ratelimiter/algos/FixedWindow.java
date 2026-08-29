package LLD.Ratelimiter.algos;

import LLD.Ratelimiter.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Fixed Window: reset counter every N seconds from creation time.
 * Simple but can allow 2x burst at window boundaries.
 */
public class FixedWindow implements RateLimiter {
    private final long creationTime;
    private final int timeWindowInSeconds;
    private final int maxBucketCapacity;
    private final AtomicInteger currentCapacity;
    private final AtomicLong lastUpdatedTime;

    public FixedWindow(int maxBucketCapacity, int timeWindowInSeconds) {
        this.maxBucketCapacity = maxBucketCapacity;
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.currentCapacity = new AtomicInteger(maxBucketCapacity);
        this.creationTime = System.currentTimeMillis();
        this.lastUpdatedTime = new AtomicLong(this.creationTime);
    }

    @Override
    public boolean grantAccess() {
        long curTime = System.currentTimeMillis();
        refresh(curTime);
        if (currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refresh(long curTime) { // refill capacity when window expires
        int curDiffInSeconds = (int) ((curTime - creationTime) / 1000);
        int lastUpdateDiffSeconds = (int) ((lastUpdatedTime.get() - creationTime) / 1000);
        if (curDiffInSeconds - lastUpdateDiffSeconds >= timeWindowInSeconds) {
            currentCapacity.set(maxBucketCapacity);
            lastUpdatedTime.set(curTime);
        }
    }
}
