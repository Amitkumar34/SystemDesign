package LLD.Ratelimiter.algos;

import LLD.Ratelimiter.RateLimiter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Token Bucket: tokens refill at fixed rate; burst allowed up to max capacity.
 * Good for: smooth traffic with occasional bursts.
 */
public class TokenBucket implements RateLimiter {
    private final int maxBucketCapacity;
    private final int tokenAdditionRatePerSeconds;
    private final AtomicInteger currentCapacity;
    private final AtomicLong lastUpdatedTime;

    public TokenBucket(int maxBucketCapacity, int tokenAdditionRatePerSeconds) {
        this.maxBucketCapacity = maxBucketCapacity;
        this.tokenAdditionRatePerSeconds = tokenAdditionRatePerSeconds;
        currentCapacity = new AtomicInteger(maxBucketCapacity);
        lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean grantAccess() {
        refreshBucket();
        if (currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refreshBucket() { // add tokens based on elapsed time since last refresh
        long curTime = System.currentTimeMillis();
        int addToken = (int) ((curTime - lastUpdatedTime.get()) / 1000 * tokenAdditionRatePerSeconds);
        int newCap = Math.min(currentCapacity.get() + addToken, maxBucketCapacity);
        currentCapacity.set(newCap);
        lastUpdatedTime.set(curTime);
    }
}
